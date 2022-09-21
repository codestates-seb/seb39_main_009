package teamparkinglot.parkinggo.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.parking.dto.ParkingCondDto;
import teamparkinglot.parkinggo.parking.dto.ParkingMapDto;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;
import teamparkinglot.parkinggo.parking.dto.ParkingResDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.mapper.ParkingMapper;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper mapper;

    @GetMapping("/parking/{id}")
    public ResponseEntity viewParking(@PathVariable long id) {

        Parking parking = parkingService.findById(id);
        ParkingResDto parkingResDto = mapper.parkingToParkingResDto(parking);

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
    public ResponseEntity payButton(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity searchHistory(Authentication authentication) {

        String email = null;

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            email = principalDetails.getUsername();
        }

        List<ParkingRecentDto> recentSearches = parkingService.findRecentSearches(email);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find")
    public ResponseEntity searchParking(Authentication authentication,
                                        @RequestBody ParkingCondDto parkingCondDto) {

//        PrincipalDetails principalDetails = null;
//
//        if (authentication != null) {
//            principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        }

        List<Parking> byCond = parkingService.findByCond(parkingCondDto);
        List<ParkingResDto> collect = byCond.stream()
                .map(e -> mapper.parkingToParkingResDto(e))
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
}
