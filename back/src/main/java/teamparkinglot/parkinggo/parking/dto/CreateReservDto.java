package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservDto {

    private Long reservNum;
    private LocalDateTime reservationDate;
    private LocalDateTime parkingStartTime;
    private String carNumber;
}
