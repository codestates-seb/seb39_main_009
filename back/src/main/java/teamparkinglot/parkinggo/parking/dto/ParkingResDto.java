package teamparkinglot.parkinggo.parking.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkingResDto {

    private long parkingId;
    private String name;
    private String address;
    private String weekdayOpen;
    private String weekdayClose;
    private String tel;
    private int basicTime;
    private int basicCharge;
    private int addUnitTime;
    private int addUnitCharge;
    private int capacity;
    private String satOpen;
    private String satClose;
    private String sunOpen;
    private String sunClose;
    private Boolean partnership;
    private String parkingSeparation;
    private String parkingType;
    private String spacial_management;
    private String parkingChargeInfo;
    private String methodPay;
    private int dayMaxPrice;
    private Double latitude;
    private Double longitude;
    private String parkingMap;
    boolean bookmark;

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Builder
    public ParkingResDto(long parkingId, String name, String address, String weekdayOpen, String weekdayClose, String tel, int basicTime, int basicCharge, int addUnitTime, int addUnitCharge, int capacity, String satOpen, String satClose, String sunOpen, String sunClose, Boolean partnership, String parkingSeparation, String parkingType, String spacial_management, String parkingChargeInfo, String methodPay, int dayMaxPrice, Double latitude, Double longitude, String parkingMap) {
        this.parkingId = parkingId;
        this.name = name;
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
        this.spacial_management = spacial_management;
        this.parkingChargeInfo = parkingChargeInfo;
        this.methodPay = methodPay;
        this.dayMaxPrice = dayMaxPrice;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parkingMap = parkingMap;
    }
}
