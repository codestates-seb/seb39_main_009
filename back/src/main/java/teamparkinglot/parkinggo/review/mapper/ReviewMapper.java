package teamparkinglot.parkinggo.review.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.Review;

@Component
public class ReviewMapper {


    public ReviewResDto reviewsToReviewsResDto(Review review) {
        return ReviewResDto.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .star(review.getStar())
                .nickName(review.getMember().getNickname())
                .body(review.getBody())
                .build();
    }
}
