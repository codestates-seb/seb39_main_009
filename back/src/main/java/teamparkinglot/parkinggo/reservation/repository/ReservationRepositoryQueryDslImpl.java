package teamparkinglot.parkinggo.reservation.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.parking_place.QParkingPlace;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static teamparkinglot.parkinggo.member.entity.QMember.*;
import static teamparkinglot.parkinggo.parking_place.QParkingPlace.*;
import static teamparkinglot.parkinggo.reservation.entity.QReservation.*;

@Repository
public class ReservationRepositoryQueryDslImpl implements ReservationRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryQueryDslImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Reservation findReservation(Long id) {
        return queryFactory
                .selectFrom(reservation)
                .join(reservation.member, member)
                .fetchJoin()
                .where(reservation.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<ReservationListDto> findReservationList(String email) {
        return queryFactory
                .select(Projections.fields(ReservationListDto.class,
                                reservation.parkingPlace.parking.parkingName.as("name"), reservation.parkingPlace.number, reservation.id.as("reservNum"),
                                reservation.parkingStartDateTime.as("parkingStartTime"), reservation.parkingEndDateTime.as("parkingEndTime")))
                .from(reservation)
                .where(reservation.member.email.eq(email).and(reservation.payOrNot.isTrue()))
                .orderBy(reservation.id.desc())
                .fetch();
    }

    @Override
    public ReservationResponseDto findByReservId(Long id) {
        return queryFactory
                .select(Projections.fields(ReservationResponseDto.class,
                        reservation.member.email, reservation.member.phone.as("phoneNumber"), reservation.id.as("reservationId"),
                        reservation.parkingPlace.parking.parkingName, reservation.parkingPlace.number.as("parkingPlaceNumber"),
                        reservation.parkingStartDateTime, reservation.parkingEndDateTime, reservation.price, reservation.parkingPlace.parking.id.as("parkingId")))
                .from(reservation)
                .where(reservation.id.eq(id))
                .fetchOne();
    }
}
