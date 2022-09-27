package teamparkinglot.parkinggo.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reservation> reservation;
    private String carNumber;
    private String phone;
    private Long point;
    private Boolean psInfoAgmt;
    private Boolean svcUseAgmt;
    private Boolean eventAgmt;

    private String provider;
    private String providerId;

    @Builder
    public Member(String email, String password, String nickname, MemberRole role, List<Reservation> reservation, String carNumber, String phone, Long point, Boolean psInfoAgmt, Boolean svcUseAgmt, Boolean eventAgmt, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.reservation = reservation;
        this.carNumber = carNumber;
        this.phone = phone;
        this.point = point;
        this.psInfoAgmt = psInfoAgmt;
        this.svcUseAgmt = svcUseAgmt;
        this.eventAgmt = eventAgmt;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
