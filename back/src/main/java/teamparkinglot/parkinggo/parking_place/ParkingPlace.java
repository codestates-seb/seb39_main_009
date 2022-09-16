package teamparkinglot.parkinggo.parking_place;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class ParkingPlace {

    @Id @GeneratedValue
    private Long id;

    private String sector;
    private Integer number;

//    @ManyToOne
//    private Parking parking;
}
