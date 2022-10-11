package teamparkinglot.parkinggo.reservation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.dto.CreateReservDto;
import teamparkinglot.parkinggo.parking.dto.ParkingDateTimeDto;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.parking_place.ParkingPlaceRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReservationServiceTest {
    @Autowired private ReservationService reservationService;
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private ParkingRepository parkingRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingPlaceRepository parkingPlaceRepository;

    Member member;
    Member member2;
    Parking parking;
    Parking parking2;
    ParkingPlace parkingPlace;
    Reservation reservation;
    Reservation reservation2;
    Reservation reservation3;

    @BeforeEach
    void set() {
        member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        member2 = new Member("email2", "password1", "nickname1", MemberRole.USER, null, "carNumber2",
                "phone1", 500L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
        memberRepository.save(member2);
        parking = new Parking("1", "parkingName", new Address("1", "papa", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", false, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parking2 = new Parking("2", "parkingName2", new Address("zip", "str", "par"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parkingRepository.save(parking);
        parkingRepository.save(parking2);
        parkingPlace = new ParkingPlace(3, parking2);
        parkingPlaceRepository.save(parkingPlace);

        reservation = new Reservation(
                LocalDateTime.of(2022, 10, 11, 7, 0, 0),
                LocalDateTime.of(2022, 12, 11, 8, 0, 0),
                LocalDateTime.of(2022, 12, 1, 8, 30, 0),
                true, 1000L, false, member, parkingPlace);
        reservation2 = new Reservation(
                LocalDateTime.of(2022, 1, 1, 10, 0, 0),
                LocalDateTime.of(2022, 1, 1, 11, 0, 0),
                LocalDateTime.of(2022, 1, 1, 11, 30, 0),
                true, 1000L, false, member2, parkingPlace);
        reservation3 = new Reservation(
                LocalDateTime.of(2022, 10, 4, 14, 0, 0),
                LocalDateTime.of(2022, 10, 5, 14, 0, 0),
                LocalDateTime.of(2022, 10, 5, 14, 30, 0),
                true, 1000L, false, member, parkingPlace);
        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
    }

    /*결제성공*/
    @Test
    public void finalPayment() throws Exception {
        //given

        //when
        reservationService.finalPayment(reservation.getId());
        //then
        assertEquals(reservation.getMember().getPoint(), 9000L);
        assertEquals(reservation.getPayOrNot(), true);
    }

    /*결제실패(포인트부족)*/
    @Test
    public void finalPaymentFailure() throws Exception {
        //given

        //when

        //then
        assertThrows(BusinessException.class, () -> reservationService.finalPayment(reservation2.getId()), "포인트 부족");
    }

    /*예약취소*/
    @Test
    public void cancelPayment() throws Exception {
        //given
        reservationService.finalPayment(reservation.getId());
        //when
        reservationService.cancelPayment(reservation.getId());
        Optional<Reservation> byId = reservationRepository.findById(reservation.getId());
        //then
        assertEquals(byId, Optional.empty());
        assertEquals(reservation.getMember().getPoint(), 10000L);
    }

    /*예약취소실패(예약시작시간 1시간 이내)*/
    @Test
    public void cancelPaymentFailure() throws Exception {
        //given
        reservationService.finalPayment(reservation3.getId());
        //when

        //then
        assertThrows(BusinessException.class, () -> reservationService.cancelPayment(reservation3.getId()));
    }
}