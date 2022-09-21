package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyPageResDto {

    Long id;
    String email;
    String phoneNum;
    String carNumber;
}
