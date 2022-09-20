package teamparkinglot.parkinggo.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import javax.persistence.EntityManager;

import static teamparkinglot.parkinggo.member.entity.QMember.*;
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
}
