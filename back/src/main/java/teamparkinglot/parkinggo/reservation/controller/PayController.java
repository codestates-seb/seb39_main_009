package teamparkinglot.parkinggo.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PayController {

    private final ReservationService reservationService;

    @PostMapping("/pay/{id}")
    public ResponseEntity createReservation(@PathVariable Long id,
                                            Authentication authentication) {

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String email = principal.getUsername();

        reservationService.finalPayment(id, email);

        // 일정시간 후 checkPayment 1번만 실행
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if(count++ < 1) reservationService.checkPayment(id);
                else timer.cancel();
            }
        };
        timer.schedule(timerTask, 10000, 1000);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pay/{id}")
    public ResponseEntity cancelPay(@PathVariable Long id) {

        reservationService.cancelPayment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
