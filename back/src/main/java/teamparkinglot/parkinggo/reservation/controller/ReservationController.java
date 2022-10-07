package teamparkinglot.parkinggo.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/pay/{reservationId}")
    public ResponseEntity finalPay(@PathVariable Long reservationId,
                                            Authentication authentication) {

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String email = principal.getUsername();

        reservationService.finalPayment(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pay/{reservationId}")
    public ResponseEntity cancelPay(@PathVariable Long reservationId) {

        reservationService.cancelPayment(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
