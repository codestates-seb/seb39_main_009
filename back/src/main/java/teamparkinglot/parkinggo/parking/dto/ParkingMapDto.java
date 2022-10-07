package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingMapDto {

    private String imageURL;
    private String parkingName;
    private List<ValidNum> validNum;

}
