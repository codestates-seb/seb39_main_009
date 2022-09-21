package teamparkinglot.parkinggo.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.review.entity.Review;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findTop10ByOrderById();

    @Query("select p from Parking p where p.id IN (:ids)")
    List<Parking> findAllByid(@Param("ids") List<Long> ids);
}
