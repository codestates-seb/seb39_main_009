package teamparkinglot.parkinggo.parking.entity;

import lombok.Getter;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.review.entity.Review;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Address address;
    private String weekdayOpen;
    private String weekdayClose;
    private String tel;
    private int price;
    private int capacity;
    private String satOpen;
    private String satClose;
    private String sunOpen;
    private String sunClose;
    private boolean partnership;
    private String type;
    private int dayMax;
    private double LAT;
    private double LNG;
    private String parkingMap;
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
//    @OneToMany
//    private Bookmark bookmark;
    @OneToMany(mappedBy = "parking")
    private List<Review> reviews;
}
