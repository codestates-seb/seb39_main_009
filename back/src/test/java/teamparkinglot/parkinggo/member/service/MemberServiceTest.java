package teamparkinglot.parkinggo.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.member.dto.MyPagePutDto;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.member.dto.SidebarDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.parking_place.ParkingPlaceRepository;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingRepository parkingRepository;
    @Autowired private ParkingPlaceRepository parkingPlaceRepository;
    @Autowired private ReservationRepository reservationRepository;

    Member member;

    @BeforeEach
    void set() {
        member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
    }
    @Test
    public void memberCreate() throws Exception {
        //given

        //when
        memberService.memberCreate(member);
        //then
        Optional<Member> byEmail = memberRepository.findByEmail(member.getEmail());
        assertEquals(byEmail.get().getPhone(), member.getPhone());
    }

    @Test
    public void myPageModify() throws Exception {
        //given
        memberRepository.save(member);
        MyPagePutDto myPagePutDto = new MyPagePutDto("abcd1234!", "010-1111-1111", "12하1212");
        //when
        memberService.myPageModify(myPagePutDto, member.getEmail());
        //then
        assertEquals(member.getPhone(), myPagePutDto.getPhoneNum());
        assertEquals(member.getCarNumber(), myPagePutDto.getCarNumber());
    }

    @Test
    public void viewSidebar() throws Exception {
        //given
        memberRepository.save(member);
        //when
        SidebarDto sidebarDto = memberService.viewSidebar(member.getEmail());
        //then
        assertEquals(sidebarDto.getCarNumber(), member.getCarNumber());
        assertEquals(sidebarDto.getName(), member.getNickname());
    }

    @Test
    public void viewReservations() throws Exception {
        //given
        memberRepository.save(member);
        Parking parking = new Parking("1", "parkingName", new Address("1", "papa", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", false, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member);
        parkingRepository.save(parking);
        ParkingPlace parkingPlace = new ParkingPlace(3, parking);
        parkingPlaceRepository.save(parkingPlace);
        Reservation reservation = new Reservation(
                LocalDateTime.of(2022, 10, 11, 7, 0, 0),
                LocalDateTime.of(2022, 10, 11, 8, 0, 0),
                LocalDateTime.of(2022, 10, 1, 8, 30, 0),
                true, 1000L, true, member, parkingPlace);
        reservationRepository.save(reservation);
        //when
        List<ReservationListDto> reservationListDtos = memberService.viewReservations(member.getEmail());
        //then
        assertEquals(reservationListDtos.get(0).getName(), parking.getParkingName());
    }

}