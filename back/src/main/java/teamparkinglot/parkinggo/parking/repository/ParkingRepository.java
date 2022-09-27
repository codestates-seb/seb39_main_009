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

    @Query("select p from Parking p where p.parkingManagementNumber = :parkingManagementNumber")
    Optional<Parking> findByParkingManagementNumber(@Param("parkingManagementNumber") String parkingManagementNumber);

    @Query("select distinct p from Parking p left outer join Reservation r on r.parkingPlace.id = p.id" +
            " where ((p.address.parcel like %:region% or p.address.street like %:region%) and (r.parkingStartDateTime not between :parkingStartTime and :parkingEndTime) and (r.parkingEndDateTime not between :parkingStartTime and :parkingEndTime)) or " +
            "(p.address.parcel like %:region% or p.address.street like %:region%) order by p.partnership desc")
    List<Parking> testMethod(@Param("region") String region, @Param("parkingStartTime") LocalDateTime parkingStartTime, @Param("parkingEndTime") LocalDateTime parkingEndTime);
}
