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

    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parking parking;

    //테스트용
    public ParkingPlace(Integer number, Parking parking) {
        this.number = number;
        this.parking = parking;
    }
}
