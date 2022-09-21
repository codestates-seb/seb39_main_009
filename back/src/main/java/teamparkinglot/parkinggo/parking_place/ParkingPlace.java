package teamparkinglot.parkinggo.parking_place;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.parking.entity.Parking;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ParkingPlace {

    @Id @GeneratedValue
    private Long id;

    private String sector;
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parking parking;
}
