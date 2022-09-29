package teamparkinglot.parkinggo.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r join fetch r.parkingPlace where r.parkingPlace.parking.id = :parkingId")
    List<Reservation> findByParkingId(@Param("parkingId") long parkingId);

    @Query("select r from Reservation r where r.parkingPlace.parking.id = :parkingId and r.member.id = :memberId")
    List<Reservation> findByParkingAndMember(@Param("parkingId") long parkingId, @Param("memberId") long memberId);
}
