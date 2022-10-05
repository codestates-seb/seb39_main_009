package teamparkinglot.parkinggo.notice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.advice.util.Auditable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Auditable {

    @Id @GeneratedValue
    private Long id;
    private String title;

    private String body;

    private Boolean use;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Notice(String title, String body, Boolean use, NoticeType type, Member member) {
        this.title = title;
        this.body = body;
        this.use = use;
        this.type = type;
        this.member = member;
    }
}
