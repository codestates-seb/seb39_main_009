package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {

    private long memberId;
    private String email;
    private String name;

}
