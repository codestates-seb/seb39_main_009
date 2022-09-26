package teamparkinglot.parkinggo.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findTop10ByOrderById();

    @Query("select p from Parking p where p.id IN (:ids)")
    List<Parking> findAllByid(@Param("ids") List<Long> ids);

    @Query("select p from Parking p where p.parkingManagementNumber = :parkingManagementNumber")
    Optional<Parking> findByParkingManagementNumber(@Param("parkingManagementNumber") String parkingManagementNumber);

    @Query("select distinct p from Parking p left outer join ParkingPlace pp on p.id = pp.parking.id inner join Reservation r on r.parkingPlace.id = pp.id" +
            " where (p.address.parcel like %:region% or p.address.street like %:region%) or " +
            "((r.parkingStartDateTime not between :parkingStartTime and :parkingEndTime) and (r.parkingEndDateTime not between :parkingStartTime and :parkingEndTime))")
    List<Parking> testMethod(@Param("region") String region, @Param("parkingStartTime") LocalDateTime parkingStartTime, @Param("parkingEndTime") LocalDateTime parkingEndTime);
}
