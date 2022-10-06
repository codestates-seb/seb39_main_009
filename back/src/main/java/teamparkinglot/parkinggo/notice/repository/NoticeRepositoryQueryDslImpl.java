package teamparkinglot.parkinggo.notice.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.entity.QNotice;

import java.util.List;

import static teamparkinglot.parkinggo.notice.entity.QNotice.*;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryQueryDslImpl implements NoticeRepositoryQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<NoticeResDto> findNoticeAll(Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(notice.inUse.isTrue());

        List<NoticeResDto> content = query.select(Projections.fields(NoticeResDto.class,
                        notice.type, notice.title, notice.body, notice.createdDate, notice.modifiedDate))
                .from(notice)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(notice.count())
                .from(notice)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
