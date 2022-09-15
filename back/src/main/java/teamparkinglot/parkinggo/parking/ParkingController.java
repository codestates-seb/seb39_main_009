package teamparkinglot.parkinggo.parking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/find")
    public ResponseEntity searchHistory() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
