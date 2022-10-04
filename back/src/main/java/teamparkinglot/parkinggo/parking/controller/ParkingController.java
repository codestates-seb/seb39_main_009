package teamparkinglot.parkinggo.parking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.history.service.HistoryService;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.mapper.ParkingMapper;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.review.service.ReviewService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper mapper;
    private final HistoryService historyService;
    private final ReservationService reservationService;
    private final ReviewService reviewService;

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

    @GetMapping("/parking/{id}/star")
    public ResponseEntity viewAverageStarInParking(@PathVariable long id) {

        double star = Math.round(reviewService.getAverageStar(id) * 10) / 10.0;
        ParkingStarResponseDto responseDto = new ParkingStarResponseDto(star);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/parking/{id}/reservation")
    public ResponseEntity parkingMap(@PathVariable long id,
                                     @RequestParam("parkingStartDateTime") String parkingStartDateTime,
                                     @RequestParam("parkingEndDateTime") String parkingEndDateTime){

        SelectTimeDto selectTimeDto = new SelectTimeDto(parkingStartDateTime, parkingEndDateTime);

        ParkingMapDto map = parkingService.findMap(id, selectTimeDto);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 아직 사용 안하는 친구
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

    @GetMapping("/parking")
    public ResponseEntity searchParking(@RequestParam("region") String region,
                                        @RequestParam("parkingStartDateTime") String parkingStartDateTime,
                                        @RequestParam("parkingEndDateTime") String parkingEndDateTime,
                                        @RequestParam("sort") String sort,
                                        @RequestParam("crtLocation") String crtLocation) {

        ParkingCondDto parkingCondDto = new ParkingCondDto(region, parkingStartDateTime, parkingEndDateTime, null, null);

        List<Parking> byCond = parkingService.findByCond(parkingCondDto);
        List<ParkingResDto> collect = byCond.stream()
                .map(e -> mapper.parkingToParkingResDto(e))
                .limit(10)
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }


}
