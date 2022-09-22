package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRecentDto {

    private String parkingName;
    private String address;

//    @Builder
//    public ParkingRecentDto(String parkingName, String address) {
//        this.parkingName = parkingName;
//        this.address = address;
//    }
}
