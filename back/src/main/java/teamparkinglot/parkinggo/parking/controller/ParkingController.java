package teamparkinglot.parkinggo.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.history.History;
import teamparkinglot.parkinggo.history.HistoryRepository;
import teamparkinglot.parkinggo.history.service.HistoryService;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.mapper.ParkingMapper;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper mapper;
    private final HistoryService historyService;
    private final ReservationService reservationService;

    @GetMapping("/parking/{id}")
    public ResponseEntity viewParking(@PathVariable long id,
                                      Authentication authentication) {

        Parking parking = parkingService.findVerifiedParking(id);
        ParkingResDto parkingResDto = mapper.parkingToParkingResDto(parking);

        if (authentication != null) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            historyService.saveHistory(id, principal.getUsername());
        }

        return new ResponseEntity<>(parkingResDto, HttpStatus.OK);
    }

    @GetMapping("/parking/{id}/reservation")
    public ResponseEntity parkingMap(@PathVariable long id) {

        ParkingMapDto map = parkingService.findMap(id);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/parking/{id}/reservation/{number}")
    public ResponseEntity checkTime(@PathVariable long id, @PathVariable int number) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/parking/{id}/reservation")
    public ResponseEntity payButton(@PathVariable long id, @RequestBody ParkingDateTimeDto parkingDateTimeDto,
                                    Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String email = principalDetails.getUsername();

        CreateReservDto reservation = parkingService.createReservation(id, parkingDateTimeDto, email);

        // 일정시간 후 checkPayment 1번만 실행, 비동기
        reservationService.reservationPaymentCheck(id);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/history")
    public ResponseEntity searchHistory(Authentication authentication) {

        String email = null;

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            email = principalDetails.getUsername();
        }

        List<ParkingRecentDto> recentSearches = parkingService.findRecentSearches(email);

        return new ResponseEntity<>(recentSearches, HttpStatus.OK);
    }

    @GetMapping("/parking")
    public ResponseEntity searchParking(Authentication authentication,
                                        @RequestParam("region") String region,
                                        @RequestParam("parkingStartDateTime") String parkingStartDateTime,
                                        @RequestParam("parkingEndDateTime") String parkingEndDateTime,
                                        @RequestParam("sort") String sort,
                                        @RequestParam("crtLocation") String crtLocation) {

        ParkingCondDto parkingCondDto = new ParkingCondDto(region, parkingStartDateTime, parkingEndDateTime, sort, crtLocation);
        System.out.println("parkingCondDto = " + parkingCondDto.getRegion());

        List<Parking> byCond = parkingService.findByCond(parkingCondDto);
        List<ParkingResDto> collect = byCond.stream()
                .map(e -> mapper.parkingToParkingResDto(e))
                .limit(10)
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }


}
