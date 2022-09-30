package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPwdDto {

    @NotBlank
    @Pattern(regexp = "^.*(?=^.{8,25}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호는 숫자, 영어, 특수문자 포함 8자리 이상이어야 합니다.")
    private String password;
}
