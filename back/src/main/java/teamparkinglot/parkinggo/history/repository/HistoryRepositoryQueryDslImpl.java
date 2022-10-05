package teamparkinglot.parkinggo.history.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;

import java.util.List;

import static teamparkinglot.parkinggo.history.entity.QHistory.*;

@Repository
public class HistoryRepositoryQueryDslImpl implements HistoryRepositoryQueryDsl{

    private final JPAQueryFactory queryFactory;

    public HistoryRepositoryQueryDslImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }

    public List<ParkingRecentDto> findRecentSearch(String email) {
        return queryFactory
                .select(Projections.fields(ParkingRecentDto.class,
                       history.parking.id.as("parkingId") , history.parking.parkingName.as("parkingName"), history.parking.address.parcel.as("address")))
                .from(history)
                .where(history.member.email.eq(email))
                .orderBy(history.id.desc())
                .limit(5)
                .fetch();
    }
}
