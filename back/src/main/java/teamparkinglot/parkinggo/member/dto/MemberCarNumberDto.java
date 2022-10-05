package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCarNumberDto {

    @NotBlank
    @Pattern(regexp = "^[가-힣]{2}\\d{2}[가-힣]\\d{4}$|^\\d{2,3}[가-힣]\\d{4}$", message = "올바른 차량번호를 입력해주세요.")
    private String carNumber;

}
