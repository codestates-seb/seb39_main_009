package teamparkinglot.parkinggo.reservation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime reservationDate;

    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
    private Boolean refundAgmt;
    private Long price;
    private Boolean payOrNot;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private ParkingPlace parkingPlace;

    public void setPayOrNot(Boolean payOrNot) {
        this.payOrNot = payOrNot;
    }
}
