package teamparkinglot.parkinggo.reservation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.parking.dto.CreateReservDto;
import teamparkinglot.parkinggo.parking.dto.ParkingDateTimeDto;
import teamparkinglot.parkinggo.parking.dto.ParkingMapDto;
import teamparkinglot.parkinggo.parking.dto.SelectTimeDto;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final ParkingService parkingService;

    @PostMapping("/pay/{reservationId}")
    public ResponseEntity finalPay(@PathVariable Long reservationId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getUsername();
        log.info("예약 최종 결제! 요청 email = {}", email);

        reservationService.finalPayment(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pay/{reservationId}")
    public ResponseEntity cancelPay(@PathVariable Long reservationId) {

        reservationService.cancelPayment(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member/reservation")
    public ResponseEntity reservationList(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = principalDetails.getUsername();

        List<ReservationListDto> reservationListDto = memberService.viewReservations(email);

        return new ResponseEntity<>(reservationListDto, HttpStatus.OK);
    }

    @GetMapping("/member/reservation/{id}")
    public ResponseEntity viewReservation(@PathVariable Long id) {

        ReservationResponseDto reservationResponseDto = reservationService.findByIdForReservationDto(id);

        return new ResponseEntity<>(reservationResponseDto, HttpStatus.OK);
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
    public ResponseEntity payButton(@PathVariable long id,
                                    @RequestBody ParkingDateTimeDto parkingDateTimeDto,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String email = principalDetails.getUsername();
        log.info("예약 활성화! 요청 email = {}", email);

        CreateReservDto reservation = parkingService.createReservation(id, parkingDateTimeDto, email);

        // 일정시간 후 checkPayment 1번만 실행, 비동기
        reservationService.reservationPaymentCheck(id);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}
