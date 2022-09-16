package teamparkinglot.parkinggo.parking.entity;

import lombok.Getter;
import teamparkinglot.parkinggo.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Address address;
    private LocalDateTime weekday_open;
    private LocalDateTime weekday_close;
    private String tel;
    private int price;
    private int capacity;
    private LocalDateTime sat_open;
    private LocalDateTime sat_close;
    private LocalDateTime sun_open;
    private LocalDateTime sun_close;
    private boolean partnership;
    private String type;
    private int day_max;
    private double LAT;
    private double LNG;
    private String parking_map;
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
//    @OneToMany
//    private Bookmark bookmark;
//    @OneToMany
//    private Review review;
}
