package teamparkinglot.parkinggo.parking.repository;

import com.querydsl.core.BooleanBuilder;
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

    public List<Parking> findByRegion(String region) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(parking.address.parcel.like("%" + region + "%"));

        return query.select(parking)
                .from(parking)
                .where(builder)
                .fetch();
    }

    public List<Parking> findParkingOnRegionAndReservationTime(String region, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(parking.address.parcel.like("%" + region + "%").or(parking.address.street.like("%" + region + "%")))
                .and(reservation.isNull().or(reservation.parkingStartDateTime.notBetween(parkingStartTime, parkingEndTime).and(reservation.parkingEndDateTime.notBetween(parkingStartTime, parkingEndTime))));


        return query.select(parking).distinct()
                .from(reservation)
                .rightJoin(reservation.parkingPlace, parkingPlace)
                .leftJoin(parkingPlace.parking, parking)
                .where(builder)
                .orderBy(parking.partnership.desc())
                .fetch();
    }
}
