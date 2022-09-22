package teamparkinglot.parkinggo.history;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;

import javax.persistence.EntityManager;
import java.util.List;

import static teamparkinglot.parkinggo.history.QHistory.*;

@Repository
public class HistoryRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    public HistoryRepositoryQueryDsl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ParkingRecentDto> findRecentSearch(String email) {
        return queryFactory
                .select(Projections.fields(ParkingRecentDto.class,
                        history.parking.name.as("parkingName"), history.parking.address.parcel.as("address")))
                .from(history)
                .where(history.member.email.eq(email))
                .orderBy(history.id.desc())
                .limit(5)
                .fetch();
    }
}
