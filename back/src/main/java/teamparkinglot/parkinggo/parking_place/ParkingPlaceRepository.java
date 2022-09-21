package teamparkinglot.parkinggo.parking_place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    @Query("select p from ParkingPlace p where p.parking.id = :parkingId and p.number = :number")
    ParkingPlace findParkingPlace(Long parkingId, Integer number);
}
