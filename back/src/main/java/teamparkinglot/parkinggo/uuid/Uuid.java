package teamparkinglot.parkinggo.uuid;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Uuid {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
    private String uuid;

    public Uuid(Member member, String uuid) {
        this.member = member;
        this.uuid = uuid;
    }
}
