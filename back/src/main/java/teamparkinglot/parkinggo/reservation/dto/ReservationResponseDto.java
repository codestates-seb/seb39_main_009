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
    String reservationNumber;
    String parkingName;
    String parkingPlaceNumber;
    LocalDateTime ParkingStartDateTime;
    LocalDateTime ParkingEndDateTime;
    long price;

    @Builder
    public ReservationResponseDto(String email, String phoneNumber, String reservationNumber, String parkingName, String parkingPlaceNumber, LocalDateTime parkingStartDateTime, LocalDateTime parkingEndDateTime, long price) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservationNumber = reservationNumber;
        this.parkingName = parkingName;
        this.parkingPlaceNumber = parkingPlaceNumber;
        ParkingStartDateTime = parkingStartDateTime;
        ParkingEndDateTime = parkingEndDateTime;
        this.price = price;
    }
}
