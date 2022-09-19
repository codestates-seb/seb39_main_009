package teamparkinglot.parkinggo.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@EnableScheduling
public class ReservationService {
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public void finalPayment(Long id, String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS));

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionCode.RESERVATION_NOT_EXISTS));

        Long currentPoint = member.getPoint();
        Long minusPoint = reservation.getPrice();

        if(currentPoint >= minusPoint) {
            member.setPoint(currentPoint - minusPoint);
            reservation.setPayOrNot(true);
        }
        else throw new BusinessException(ExceptionCode.POINT_NOT_ENOUGH);

    }

    @Transactional
    public void cancelPayment(Long id) {
        Reservation reservation = findVerifiedReservation(id);

        reservationRepository.delete(reservation);
    }

//    @Scheduled(cron = "10 * * * *")
//    public void checkPayment(Long id) {
//        Reservation reservation = findVerifiedReservation(id);
//        if(!reservation.getPayOrNot()) cancelPayment(id);
//    }

    public Reservation findVerifiedReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        Reservation findReservation =
                reservation.orElseThrow(() -> new BusinessException(ExceptionCode.RESERVATION_NOT_EXISTS));

        return findReservation;
    }
}
