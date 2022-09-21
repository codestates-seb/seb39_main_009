package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingReserv {

    private long parkingId;
    private List<Reservation> reservations;

}
