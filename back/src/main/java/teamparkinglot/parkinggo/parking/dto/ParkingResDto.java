package teamparkinglot.parkinggo.parking.dto;

import lombok.AllArgsConstructor;
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
    int price;
    int capacity;
    String satOpen;
    String satClose;
    String sunOpen;
    String sunClose;
    Boolean partnership;
    String type;
    int dayMax;
    double LAT;
    double LNG;
    String parkingMap;

    @Builder
    public ParkingResDto(String name, String address, String weekdayOpen, String weekdayClose, String tel, int price, int capacity, String satOpen, String satClose, String sunOpen, String sunClose, Boolean partnership, String type, int dayMax, double LAT, double LNG, String parkingMap) {
        this.name = name;
        this.address = address;
        this.weekdayOpen = weekdayOpen;
        this.weekdayClose = weekdayClose;
        this.tel = tel;
        this.price = price;
        this.capacity = capacity;
        this.satOpen = satOpen;
        this.satClose = satClose;
        this.sunOpen = sunOpen;
        this.sunClose = sunClose;
        this.partnership = partnership;
        this.type = type;
        this.dayMax = dayMax;
        this.LAT = LAT;
        this.LNG = LNG;
        this.parkingMap = parkingMap;
    }
}
