package teamparkinglot.parkinggo.reservation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import java.util.List;

public interface ReservationRepositoryQueryDsl {

    Reservation findReservation(Long id);
    List<ReservationListDto> findReservationList(String email);

    ReservationResponseDto findByReservId(Long id);
}
