package teamparkinglot.parkinggo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationListDto {

    private String name;
    private Integer number;
    private Long reservNum;
    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
}
