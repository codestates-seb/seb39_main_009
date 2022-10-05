package teamparkinglot.parkinggo.bookmark.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;

import java.util.List;

import static teamparkinglot.parkinggo.bookmark.entity.QBookmark.*;

@Repository
public class BookmarkRepositoryQueryDslImpl implements BookmarkRepositoryQueryDsl {

    private final JPAQueryFactory query;

    public BookmarkRepositoryQueryDslImpl(JPAQueryFactory jpaQueryFactory) {
        this.query = jpaQueryFactory;
    }

    @Override
    public List<BookmarkResDto> findMyBookmarkListByEmail( String email) {
        return query
                .select(Projections.fields(BookmarkResDto.class,
                        bookmark.parking.id.as("parkingId"), bookmark.parking.parkingName.as("name"), bookmark.parking.address.parcel.as("address")))
                .from(bookmark)
                .where(bookmark.member.email.eq(email))
                .fetch();
    }
}
