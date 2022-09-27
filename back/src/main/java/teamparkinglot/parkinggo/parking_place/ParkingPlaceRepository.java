package teamparkinglot.parkinggo.parking_place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    @Query("select p from ParkingPlace p where p.parking.id = :parkingId and p.number = :number")
    ParkingPlace findParkingPlace(@Param("parkingId") Long parkingId, @Param("number") Integer number);

    @Query("select distinct p from ParkingPlace p where p.parking.id = :parkingId and not exists (select r from Reservation r where r.parkingPlace.id = p.id)")
    List<ParkingPlace> findByParkingId(@Param("parkingId") Long parkingId);
}
