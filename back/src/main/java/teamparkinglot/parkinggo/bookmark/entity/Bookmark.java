package teamparkinglot.parkinggo.bookmark.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.parking.entity.Parking;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parking parking;

    public Bookmark(Member member, Parking parking) {
        this.member = member;
        this.parking = parking;
    }
}
