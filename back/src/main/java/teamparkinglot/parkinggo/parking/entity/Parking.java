package teamparkinglot.parkinggo.parking.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamparkinglot.parkinggo.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parkingManagementNumber;
    private String parkingName;
    @Embedded
    private Address address;
    private String weekdayOpen;
    private String weekdayClose;
    private String tel;
    private Integer basicTime;
    private Integer basicCharge;
    private Integer addUnitTime;
    private Integer addUnitCharge;
    private int capacity;
    private String satOpen;
    private String satClose;
    private String sunOpen;
    private String sunClose;
    private boolean partnership;
    private String parkingSeparation;
    private String parkingType;
    private String spacialManagement;
    private String parkingChargeInfo;
    private String methodPay;
    private int dayMaxPrice;
    private Double latitude;
    private Double longitude;
    private String parkingMap;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder

    public Parking(String parkingManagementNumber, String parkingName, Address address, String weekdayOpen, String weekdayClose, String tel, Integer basicTime, Integer basicCharge, Integer addUnitTime, Integer addUnitCharge, int capacity, String satOpen, String satClose, String sunOpen, String sunClose, boolean partnership, String parkingSeparation, String parkingType, String spacialManagement, String parkingChargeInfo, String methodPay, int dayMaxPrice, Double latitude, Double longitude, String parkingMap, String phoneNumber, Member member) {
        this.parkingManagementNumber = parkingManagementNumber;
        this.parkingName = parkingName;
        this.address = address;
        this.weekdayOpen = weekdayOpen;
        this.weekdayClose = weekdayClose;
        this.tel = tel;
        this.basicTime = basicTime;
        this.basicCharge = basicCharge;
        this.addUnitTime = addUnitTime;
        this.addUnitCharge = addUnitCharge;
        this.capacity = capacity;
        this.satOpen = satOpen;
        this.satClose = satClose;
        this.sunOpen = sunOpen;
        this.sunClose = sunClose;
        this.partnership = partnership;
        this.parkingSeparation = parkingSeparation;
        this.parkingType = parkingType;
        this.spacialManagement = spacialManagement;
        this.parkingChargeInfo = parkingChargeInfo;
        this.methodPay = methodPay;
        this.dayMaxPrice = dayMaxPrice;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parkingMap = parkingMap;
        this.phoneNumber = phoneNumber;
        this.member = member;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }
}
