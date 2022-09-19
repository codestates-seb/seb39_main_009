package teamparkinglot.parkinggo.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostDto {
    private Double star;
    private String body;
}
