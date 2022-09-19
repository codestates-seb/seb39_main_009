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
    private String weekday_open;
    private String weekday_close;
    private String tel;
    private int price;
    private int capacity;
    private String sat_open;
    private String sat_close;
    private String sun_open;
    private String sun_close;
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
