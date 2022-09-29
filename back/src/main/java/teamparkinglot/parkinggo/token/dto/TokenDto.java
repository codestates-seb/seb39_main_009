package teamparkinglot.parkinggo.token.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
public class TokenDto {

    @NotBlank
    String refreshtoken;

}
