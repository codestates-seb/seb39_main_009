package teamparkinglot.parkinggo.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationResponseDto {

    String email;
    String phoneNumber;
    Long reservationNumber;
    String parkingName;
    Integer parkingPlaceNumber;
    LocalDateTime parkingStartDateTime;
    LocalDateTime parkingEndDateTime;
    Long price;

    @Builder
    public ReservationResponseDto(String email, String phoneNumber, Long reservationNumber, String parkingName, Integer parkingPlaceNumber, LocalDateTime parkingStartDateTime, LocalDateTime parkingEndDateTime, Long price) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservationNumber = reservationNumber;
        this.parkingName = parkingName;
        this.parkingPlaceNumber = parkingPlaceNumber;
        this.parkingStartDateTime = parkingStartDateTime;
        this.parkingEndDateTime = parkingEndDateTime;
        this.price = price;
    }
}
