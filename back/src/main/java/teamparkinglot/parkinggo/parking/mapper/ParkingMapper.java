package teamparkinglot.parkinggo.parking.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.parking.dto.ParkingResDto;
import teamparkinglot.parkinggo.parking.entity.Parking;

@Component
public class ParkingMapper {
    public ParkingResDto parkingToParkingResDto(Parking parking) {
        return ParkingResDto.builder()
                .name(parking.getName())
                .address(parking.getAddress().getStreet())
                .weekdayOpen(parking.getWeekdayOpen())
                .weekdayClose(parking.getWeekdayClose())
                .tel(parking.getTel())
                .price(parking.getPrice())
                .capacity(parking.getCapacity())
                .satOpen(parking.getSatOpen())
                .satClose(parking.getSatClose())
                .sunOpen(parking.getSunOpen())
                .sunClose(parking.getSunClose())
                .partnership(parking.isPartnership())
                .type(parking.getType())
                .dayMax(parking.getDayMax())
                .LAT(parking.getLat())
                .LNG(parking.getLng())
                .parkingMap(parking.getParkingMap())
        .build();
    }
}
