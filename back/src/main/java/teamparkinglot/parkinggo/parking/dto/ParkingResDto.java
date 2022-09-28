package teamparkinglot.parkinggo.parking.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkingResDto {

    String name;
    String address;
    String weekdayOpen;
    String weekdayClose;
    String tel;
    int basicTime;
    int basicCharge;
    int addUnitTime;
    int addUnitCharge;
    int capacity;
    String satOpen;
    String satClose;
    String sunOpen;
    String sunClose;
    Boolean partnership;
    String type;
    int dayMaxPrice;
    Double latitude;
    Double longitude;
    String parkingMap;
    boolean bookmark;

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Builder

    public ParkingResDto(String name, String address, String weekdayOpen, String weekdayClose, String tel, int basicTime, int basicCharge, int addUnitTime, int addUnitCharge, int capacity, String satOpen, String satClose, String sunOpen, String sunClose, Boolean partnership, String type, int dayMaxPrice, Double latitude, Double longitude, String parkingMap) {
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
        this.type = type;
        this.dayMaxPrice = dayMaxPrice;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parkingMap = parkingMap;
    }
}
