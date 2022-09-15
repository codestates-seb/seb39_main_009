package teamparkinglot.parkinggo.parking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingController {

    @GetMapping("/parking/{id}")
    public ResponseEntity viewParking(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    
}
