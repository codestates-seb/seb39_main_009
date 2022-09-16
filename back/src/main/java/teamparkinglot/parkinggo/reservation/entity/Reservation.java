package teamparkinglot.parkinggo.reservation.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Reservation {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime reservationDate;

    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
    private Boolean refundAgmt;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ParkingPlace parkingPlace;

}
