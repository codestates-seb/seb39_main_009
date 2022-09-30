package teamparkinglot.parkinggo.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResDto {

    long id;
    String nickName;
    String body;

    @Builder
    public ReviewResDto(long id, String nickName, String body) {
        this.id = id;
        this.nickName = nickName;
        this.body = body;
    }
}
