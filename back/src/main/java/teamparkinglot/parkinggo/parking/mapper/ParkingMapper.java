package teamparkinglot.parkinggo.parking.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.parking.dto.CreateReservDto;
import teamparkinglot.parkinggo.parking.dto.ParkingResDto;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.reservation.entity.Reservation;

@Component
public class ParkingMapper {
    public ParkingResDto parkingToParkingResDto(Parking parking) {
        return ParkingResDto.builder()
                .parkingId(parking.getId())
                .name(parking.getParkingName())
                .address(parking.getAddress().getStreet())
                .weekdayOpen(parking.getWeekdayOpen())
                .weekdayClose(parking.getWeekdayClose())
                .tel(parking.getTel())
                .basicTime(parking.getBasicTime())
                .basicCharge(parking.getBasicCharge())
                .addUnitTime(parking.getAddUnitTime())
                .addUnitCharge(parking.getAddUnitCharge())
                .capacity(parking.getCapacity())
                .satOpen(parking.getSatOpen())
                .satClose(parking.getSatClose())
                .sunOpen(parking.getSunOpen())
                .sunClose(parking.getSunClose())
                .partnership(parking.isPartnership())
                .parkingType(parking.getParkingType())
                .parkingSeparation(parking.getParkingSeparation())
                .methodPay(parking.getMethodPay())
                .parkingChargeInfo(parking.getParkingChargeInfo())
                .spacial_management(parking.getSpacialManagement())
                .dayMaxPrice(parking.getDayMaxPrice())
                .latitude(parking.getLatitude())
                .longitude(parking.getLongitude())
                .parkingMap(parking.getParkingMap())
        .build();
    }

    public CreateReservDto reservationToCreateReservDto(Reservation reservation, Member member) {
        return CreateReservDto.builder()
                .reservationDate(reservation.getReservationDate())
                .parkingStartTime(reservation.getParkingStartDateTime())
                .reservNum(reservation.getId())
                .carNumber(member.getCarNumber())
                .build();
    }
}
