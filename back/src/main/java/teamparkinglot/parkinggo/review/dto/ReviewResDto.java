package teamparkinglot.parkinggo.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResDto {

    long reviewId;
    long writerId;
    String nickName;
    String body;

    @Builder
    public ReviewResDto(long reviewId, long writerId, String nickName, String body) {
        this.reviewId = reviewId;
        this.writerId = writerId;
        this.nickName = nickName;
        this.body = body;
    }
}
