package teamparkinglot.parkinggo.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long>, ParkingRepositoryQueryDsl {

    @Query("select p from Parking p where p.parkingManagementNumber = :parkingManagementNumber")
    Optional<Parking> findByParkingManagementNumber(@Param("parkingManagementNumber") String parkingManagementNumber);

}
