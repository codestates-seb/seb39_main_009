package teamparkinglot.parkinggo.reservation.entity;

import com.querydsl.core.annotations.Config;
import lombok.AccessLevel;
import lombok.Builder;
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

    private LocalDateTime parkingStartDateTime;
    private LocalDateTime parkingEndDateTime;
    private Boolean refundAgmt;
    private int price;
    private Boolean payOrNot;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private ParkingPlace parkingPlace;

    public void setPayOrNot(Boolean payOrNot) {
        this.payOrNot = payOrNot;
    }

    @Builder
    public Reservation(LocalDateTime parkingStartDateTime, LocalDateTime parkingEndDateTime, Boolean refundAgmt, int price, Boolean payOrNot, Member member, ParkingPlace parkingPlace) {
        this.parkingStartDateTime = parkingStartDateTime;
        this.parkingEndDateTime = parkingEndDateTime;
        this.refundAgmt = refundAgmt;
        this.price = price;
        this.payOrNot = payOrNot;
        this.member = member;
        this.parkingPlace = parkingPlace;
    }
}
