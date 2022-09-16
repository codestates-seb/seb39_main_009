package teamparkinglot.parkinggo.bookmark.entity;

import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Bookmark {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Parking parking;
}
