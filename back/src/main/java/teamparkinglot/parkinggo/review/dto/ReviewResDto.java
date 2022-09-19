package teamparkinglot.parkinggo.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResDto {

    String nickName;
    String body;

    @Builder
    public ReviewResDto(String nickName, String body) {
        this.nickName = nickName;
        this.body = body;
    }
}
