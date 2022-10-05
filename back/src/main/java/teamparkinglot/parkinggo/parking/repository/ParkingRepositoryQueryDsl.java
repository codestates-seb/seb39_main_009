package teamparkinglot.parkinggo.parking.repository;

import teamparkinglot.parkinggo.parking.entity.Parking;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingRepositoryQueryDsl {

    List<Parking> findParkingOnRegionAndReservationTime(String region, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime);
    List<Parking> findParkingOnRegionButPartnerShipIsNot(String region);
}
