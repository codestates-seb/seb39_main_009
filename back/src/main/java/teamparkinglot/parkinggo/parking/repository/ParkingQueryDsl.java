package teamparkinglot.parkinggo.parking.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.parking.dto.ParkingCondDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.entity.QParking;

import javax.persistence.EntityManager;
import java.util.List;

import static teamparkinglot.parkinggo.parking.entity.QParking.*;

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
                .fetchJoin()
                .leftJoin(parking.reviews)
                .where(builder)
                .fetch();
    }
}
