package teamparkinglot.parkinggo.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewResDto {

    long reviewId;
    long memberId;
    double star;
    String nickName;
    String body;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    @Builder
    public ReviewResDto(long reviewId, long memberId, double star, String nickName, String body, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.star = star;
        this.nickName = nickName;
        this.body = body;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
