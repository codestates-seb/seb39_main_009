package teamparkinglot.parkinggo.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PayController {

    @PostMapping("/pay/{reservNum}")
    public ResponseEntity postPay(@PathVariable String reservNum) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pay{reservNum}")
    public ResponseEntity cancelPay(@PathVariable String reservNum) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
