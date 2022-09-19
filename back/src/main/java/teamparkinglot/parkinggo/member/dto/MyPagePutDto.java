package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPagePutDto {

    private String password;
    private String passwordRe;
    private String phoneNum;
    private String carNumber;

}
