package teamparkinglot.parkinggo.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.parking.dto.ParkingCondDto;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    @GetMapping("/parking/{id}")
    public ResponseEntity viewParking(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/parking/{id}/reservation")
    public ResponseEntity parkingMap(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/parking/{id}/reservation/{number}")
    public ResponseEntity checkTime(@PathVariable long id, @PathVariable int number) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/parking/{id}/reservation")
    public ResponseEntity payButton(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/parking/find")
    public ResponseEntity searchHistory() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/parking/find")
    public ResponseEntity searchParking(ParkingCondDto parkingCondDto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
