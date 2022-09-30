package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidebarDto {

    private String name;
    private Long memberId;
    private String email;
    private int NumOfReserv;
    private Long point;
    private String phoneNum;
    private String carNumber;
}
