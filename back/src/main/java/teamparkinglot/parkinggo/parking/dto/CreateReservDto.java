package teamparkinglot.parkinggo.parking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateReservDto {

    private Long reservNum;
    private LocalDateTime reservationDate;
    private LocalDateTime parkingStartTime;
    private String carNumber;

    @Builder
    public CreateReservDto(Long reservNum, LocalDateTime reservationDate, LocalDateTime parkingStartTime, String carNumber) {
        this.reservNum = reservNum;
        this.reservationDate = reservationDate;
        this.parkingStartTime = parkingStartTime;
        this.carNumber = carNumber;
    }
}
