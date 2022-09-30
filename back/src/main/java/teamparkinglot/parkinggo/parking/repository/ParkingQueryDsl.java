package teamparkinglot.parkinggo.parking.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.parking.dto.ParkingCondDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.entity.QParking;
import teamparkinglot.parkinggo.parking_place.QParkingPlace;
import teamparkinglot.parkinggo.reservation.entity.QReservation;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static teamparkinglot.parkinggo.parking.entity.QParking.*;
import static teamparkinglot.parkinggo.parking_place.QParkingPlace.*;
import static teamparkinglot.parkinggo.reservation.entity.QReservation.*;

@Repository
public class ParkingQueryDsl {

    private final JPAQueryFactory query;

    public ParkingQueryDsl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Parking> findParkingOnRegionAndReservationTime(String region, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(parking.address.parcel.contains(region).or(parking.address.street.contains(region)))
                .and(parkingPlace.id.notIn(
                        JPAExpressions
                                .select(parkingPlace.id).distinct()
                                .from(reservation)
                                .rightJoin(reservation.parkingPlace, parkingPlace)
                                .where(reservation.parkingStartDateTime.between(parkingStartTime, parkingEndTime)
                                        .or(reservation.parkingEndDateTime.between(parkingStartTime, parkingEndTime)))));

        return query.select(parking).distinct()
                .from(parkingPlace)
                .leftJoin(parkingPlace.parking, parking)
                .where(builder)
                .orderBy(parking.partnership.desc())
                .fetch();
    }

    public List<Parking> findParkingOnRegionButPartnerShipIsNot(String region) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(parking.address.parcel.contains(region).or(parking.address.street.contains(region)))
                .and(parking.partnership.isFalse());

        return query.select(parking)
                .from(parking)
                .where(builder)
                .limit(10)
                .fetch();
    }
}
