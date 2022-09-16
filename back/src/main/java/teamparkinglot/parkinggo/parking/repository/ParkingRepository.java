package teamparkinglot.parkinggo.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.parking.entity.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
