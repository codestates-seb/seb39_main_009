package teamparkinglot.parkinggo.review.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.util.Auditable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Auditable {

    @Id @GeneratedValue
    private Long id;

    private String body;
    private Double star;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parking parking;

    public void editReview(Double star, String body) {
        this.star = star;
        this.body = body;
    }

    @Builder
    public Review(String body, Double star, Member member, Parking parking) {
        this.body = body;
        this.star = star;
        this.member = member;
        this.parking = parking;
    }

}
