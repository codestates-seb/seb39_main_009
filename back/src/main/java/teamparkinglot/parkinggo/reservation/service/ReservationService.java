package teamparkinglot.parkinggo.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public void finalPayment(Long reservationId) {

        Reservation reservation = reservationRepository.findReservation(reservationId);

        Long currentPoint = reservation.getMember().getPoint();
        Long minusPoint = reservation.getPrice();


        if(currentPoint >= minusPoint) {
            reservation.getMember().setPoint(currentPoint - minusPoint);
            reservation.setPayOrNot(true);
        }
        else throw new BusinessException(ExceptionCode.POINT_NOT_ENOUGH);

    }

    @Transactional
    public void cancelPayment(Long reservationId) {
        Reservation reservation = findVerifiedReservation(reservationId);
        if (LocalDateTime.now().isBefore(reservation.getParkingStartDateTime().minusHours(1))) {

            if (reservation.getPayOrNot()) {
                reservation.getMember().setPoint(reservation.getMember().getPoint() + reservation.getPrice());
            }
            reservationRepository.delete(reservation);
        } else {

            if (reservation.getPayOrNot()) throw new BusinessException(ExceptionCode.NOT_BEFORE_ONE_HOURS);
            else reservationRepository.delete(reservation);
        }
    }
    public Reservation findVerifiedReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        Reservation findReservation =
                reservation.orElseThrow(() -> new BusinessException(ExceptionCode.RESERVATION_NOT_EXISTS));

        return findReservation;
    }

    public ReservationResponseDto findByIdForReservationDto(Long id) {

        ReservationResponseDto reservationResponseDto = reservationRepository.findByReservId(id);
        reservationResponseDto.setParkingEndDateTime(reservationResponseDto.getParkingEndDateTime().plusSeconds(1));
        return reservationResponseDto;
    }

    @Async
    @Transactional
    public void reservationPaymentCheck(long id) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if(count++ < 1) checkPayment(id);
                else timer.cancel();
            }
        };
        timer.schedule(timerTask, 600000, 1000);
    }

    @Transactional
    public void checkPayment(Long id)  {
        Reservation reservation = findVerifiedReservation(id);
        if(!reservation.getPayOrNot()) reservationRepository.delete(reservation);
    }
}
