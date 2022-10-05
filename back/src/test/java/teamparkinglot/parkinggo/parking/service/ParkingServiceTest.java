package teamparkinglot.parkinggo.parking.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepositoryQueryDslImpl;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.parking_place.ParkingPlaceRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ParkingServiceTest {
    @Autowired private ParkingRepository parkingRepository;
    @Autowired private ParkingService parkingService;
    @Autowired private ParkingRepositoryQueryDslImpl parkingQueryDsl;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingPlaceRepository parkingPlaceRepository;
    @Autowired private ReservationRepository reservationRepository;
    Member member;
    Parking parking;
    Parking parking2;
    ParkingPlace parkingPlace;


    @BeforeEach
    void set() {
        member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
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

    }

    @Test
    public void findByCond() throws Exception {
        //given
        ParkingCondDto parkingCondDto = new ParkingCondDto("pa", "2022-10-11 09:00", "2022-10-11 09:30", null, null);
        //when
        List<Parking> parkingList = parkingService.findByCond(parkingCondDto);

        //then
        assertEquals(parkingList.get(0).getParkingName(), parking2.getParkingName());
        assertEquals(parkingList.get(1).getParkingName(), parking.getParkingName());
    }

    @Test
    public void findMap() throws Exception {
        //given
        SelectTimeDto selectTimeDto = new SelectTimeDto("2022-10-11 09:00", "2022-10-11 09:30");
        //when
        ParkingMapDto mapDto = parkingService.findMap(parking2.getId(), selectTimeDto);
        //then
        assertEquals(mapDto.getImageURL(), parking2.getParkingMap());
        assertEquals(mapDto.getValidNum().get(0).getNumber(), parkingPlace.getNumber());
    }

    @Test
    public void createReservation() throws Exception {
        //given
        LocalDateTime start = LocalDateTime.of(2022, 1, 1, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 1, 1, 9, 0, 0);
        ParkingDateTimeDto parkingDateTimeDto = new ParkingDateTimeDto(start, end, 3);
        //when
        CreateReservDto reservation = parkingService.createReservation(parking2.getId(), parkingDateTimeDto, member.getEmail());
        //then
        assertEquals(reservation.getParkingStartTime(), parkingDateTimeDto.getParkingStartDateTime());
        assertEquals(reservation.getCarNumber(), member.getCarNumber());
    }


}