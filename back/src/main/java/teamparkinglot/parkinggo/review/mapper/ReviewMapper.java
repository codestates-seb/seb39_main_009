package teamparkinglot.parkinggo.review.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.Review;

@Component
public class ReviewMapper {


    public ReviewResDto reviewsToReviewsResDto(Review review) {
        return ReviewResDto.builder()
                .nickName(review.getMember().getNickname())
                .body(review.getBody())
                .build();
    }
}