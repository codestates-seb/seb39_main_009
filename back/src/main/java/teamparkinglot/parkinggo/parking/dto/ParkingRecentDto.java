package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRecentDto {

    private long parkingId;
    private String parkingName;
    private String address;

}
