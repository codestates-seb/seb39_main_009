package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPagePutDto {

    @NotBlank
    @Pattern(regexp = "^.*(?=^.{8,25}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 숫자, 영어, 특수문자 포함 8자리 이상이어야 합니다.")
    private String password;

    @NotBlank @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "올바른 휴대전화 번호를 입력해주세요.")
    private String phoneNum;

    @NotBlank @Pattern(regexp = "^[가-힣]{2}\\d{2}[가-힣]\\d{4}$|^\\d{2,3}[가-힣]\\d{4}$", message = "올바른 차량번호를 입력해주세요.")
    private String carNumber;

}
