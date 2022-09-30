package teamparkinglot.parkinggo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostDto {

    @NotBlank
    private Double star;

    @NotBlank
    private String body;
}
