package teamparkinglot.parkinggo.review.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teamparkinglot.parkinggo.member.entity.QMember;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.QReview;
import teamparkinglot.parkinggo.review.entity.Review;

import javax.persistence.EntityManager;

import java.util.List;


import static teamparkinglot.parkinggo.review.entity.QReview.*;

@Repository
public class ReviewRepositoryQueryDsl {
    private final JPAQueryFactory query;

    public ReviewRepositoryQueryDsl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Page<ReviewResDto> findReviewsByParkingOrderByCreatedDateDesc(Long parkingId, Pageable pageable) {
        List<ReviewResDto> content = query
                .select(Projections.fields(ReviewResDto.class,
                        review.id.as("reviewId"), review.member.id.as("memberId"), review.star, review.member.nickname.as("nickName"), review.body))
                .from(review)
                .where(review.parking.id.eq(parkingId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        int size = query
                .selectFrom(review)
                .where(review.parking.id.eq(parkingId))
                .fetch().size();

        return new PageImpl<>(content, pageable, size);
    }
}
