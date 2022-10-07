package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDto {


    @NotBlank @Email
    private String email;

    @NotBlank @Pattern(regexp = "^.*(?=^.{8,25}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 숫자, 영어, 특수문자 포함 8자리 이상이어야 합니다.")
    private String password;

    @NotBlank @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "올바른 휴대전화 번호를 입력해주세요.")
    private String phoneNum;

    @NotBlank @Pattern(regexp = "^[a-zA-Z가-힣]{2,5}$", message = "이름은 2글자 이상 5글자 이하로 작성해주세요.")
    private String name;

    @NotBlank @Pattern(regexp = "^[가-힣]{2}\\d{2}[가-힣]{1}\\d{4}$|^\\d{2,3}[가-힣]{1}\\d{4}$", message = "차량 번호를 정확히 입력해주세요.")
    private String carNumber;

    @AssertTrue
    private boolean svcUseAgmt;

    @AssertTrue
    private boolean psInfoAgmt;
    private boolean eventAgmt;

}
