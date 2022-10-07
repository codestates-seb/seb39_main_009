package teamparkinglot.parkinggo.parking_place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    @Query("select pp from ParkingPlace pp where pp.parking.id = :parkingId and pp.number = :number")
    ParkingPlace findParkingPlace(@Param("parkingId") Long parkingId, @Param("number") Integer number);

    @Query("select distinct pp from ParkingPlace pp where pp.parking.id = :parkingId and not exists (select r from Reservation r where r.parkingPlace.id = pp.id " +
            "and (:parkingStartTime between r.parkingStartDateTime and r.parkingEndDateTime or :parkingEndTime between r.parkingStartDateTime and r.parkingEndDateTime))")
    List<ParkingPlace> findByParkingIdForMapAndValidNumber(@Param("parkingId") Long parkingId, @Param("parkingStartTime") LocalDateTime parkingStartTime, @Param("parkingEndTime") LocalDateTime parkingEndTime);
}
