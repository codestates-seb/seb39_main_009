//package teamparkinglot.parkinggo.reservation.mapper;
//
//import org.springframework.stereotype.Component;
//import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
//import teamparkinglot.parkinggo.reservation.entity.Reservation;
//
//@Component
//public class ReservationMapper {
//
//    public ReservationResponseDto reservationToReservationResponseDto(Reservation reservation) {
//        return ReservationResponseDto.builder()
//                .email(reservation.getMember().getEmail())
//                .phoneNumber(reservation.getMember().getPhone())
//                .reservationNumber(reservation.getId().toString())
//                .parkingName(reservation.getParkingPlace().getParking().getParkingName())
//                .parkingPlaceNumber(reservation.getParkingPlace().getSector() + " " + reservation.getParkingPlace().getNumber())
//                .parkingStartDateTime(reservation.getParkingStartDateTime())
//                .parkingEndDateTime(reservation.getParkingEndDateTime())
//                .price(reservation.getPrice())
//                .build();
//    }
//
//}
