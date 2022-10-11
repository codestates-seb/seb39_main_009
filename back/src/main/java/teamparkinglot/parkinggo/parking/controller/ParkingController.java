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

    @GetMapping("/parking/{id}/calculation")
    public ResponseEntity calculationParkingPriceAndTime(@PathVariable long id,
                                                         @RequestParam("parkingStartDateTime") String parkingStartDateTime,
                                                         @RequestParam("parkingEndDateTime") String parkingEndDateTime,
                                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

        ParkingCalculateDto parkingCalculateDto = parkingService.calculateParkingPriceAndTime(id, parkingStartDateTime, parkingEndDateTime, principalDetails.getUsername());
        return new ResponseEntity<>(parkingCalculateDto, HttpStatus.OK);
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
