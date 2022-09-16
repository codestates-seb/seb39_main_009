package teamparkinglot.parkinggo.review.entity;

import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.util.Auditable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Review extends Auditable {

    @Id @GeneratedValue
    private Long id;

    private String body;
    private Double star;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Parking parking;
}
