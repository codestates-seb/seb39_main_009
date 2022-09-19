package teamparkinglot.parkinggo.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
