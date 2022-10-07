package teamparkinglot.parkinggo.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationListDto {

    private String name;
    private Integer number;
    private Long reservNum;
    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
}
