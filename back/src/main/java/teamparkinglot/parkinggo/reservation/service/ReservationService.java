package teamparkinglot.parkinggo.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepositoryQueryDsl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationRepositoryQueryDsl reservationRepositoryCustom;

    @Transactional
    public void finalPayment(Long id, String email) {

        Reservation reservation = reservationRepositoryCustom.findReservation(id);

        Long currentPoint = reservation.getMember().getPoint();
        Long minusPoint = reservation.getPrice();


        if(currentPoint >= minusPoint) {
            reservation.getMember().setPoint(currentPoint - minusPoint);
            reservation.setPayOrNot(true);
        }
        else throw new BusinessException(ExceptionCode.POINT_NOT_ENOUGH);

    }

    @Transactional
    public void cancelPayment(Long id) {
        Reservation reservation = findVerifiedReservation(id);

        reservationRepository.delete(reservation);
    }
    @Transactional
    public void checkPayment(Long id)  {
        Reservation reservation = findVerifiedReservation(id);
        if(!reservation.getPayOrNot()) reservationRepository.delete(reservation);
    }

    public Reservation findVerifiedReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        Reservation findReservation =
                reservation.orElseThrow(() -> new BusinessException(ExceptionCode.RESERVATION_NOT_EXISTS));

        return findReservation;
    }

    public List<Reservation> findByParkingId(long parkingId) {

        return reservationRepository.findByParkingId(parkingId);
    }
}
