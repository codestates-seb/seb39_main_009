package teamparkinglot.parkinggo.review.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
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
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReviewServiceTest {

    @Autowired private ReviewService reviewService;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ParkingRepository parkingRepository;
    @Autowired private ParkingPlaceRepository parkingPlaceRepository;
    @Autowired private ReservationRepository reservationRepository;

    Member member;
    Member member2;
    Parking parking;
    Parking parking2;
    ParkingPlace parkingPlace;
    Reservation reservation;

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
        parkingPlace = new ParkingPlace(3, parking);
        parkingPlaceRepository.save(parkingPlace);
        reservation = new Reservation(
                LocalDateTime.of(2022, 10, 11, 7, 0, 0),
                LocalDateTime.of(2022, 10, 11, 8, 0, 0),
                LocalDateTime.of(2022, 10, 1, 8, 30, 0),
                true, 1000L, false, member, parkingPlace);
        reservationRepository.save(reservation);
    }

    @Test
    public void createReview() throws Exception {
        //given
        ReviewPostDto reviewPostDto = new ReviewPostDto(3.0, "good");
        //when
        reviewService.createReview(parking.getId(), reviewPostDto, member.getEmail());
        //then
        Optional<Review> byMemberEmailAndParkingId = reviewRepository.findByMemberEmailAndParkingId(member.getEmail(), parking.getId());
        assertEquals(byMemberEmailAndParkingId.get().getBody(), reviewPostDto.getBody());
    }

    @Test
    public void editReview() throws Exception {
        //given
        ReviewPostDto reviewPostDto = new ReviewPostDto(3.0, "good");
        reviewService.createReview(parking.getId(), reviewPostDto, member.getEmail());
        //when
        reviewService.editReview(member.getEmail(), parking.getId(), 2.0, "bad");
        //then
        Optional<Review> byMemberEmailAndParkingId = reviewRepository.findByMemberEmailAndParkingId(member.getEmail(), parking.getId());
        assertEquals(byMemberEmailAndParkingId.get().getStar(), 2.0);
        assertEquals(byMemberEmailAndParkingId.get().getBody(), "bad");
    }

    @Test
    public void deleteReview() throws Exception {
        //given
        ReviewPostDto reviewPostDto = new ReviewPostDto(3.0, "good");
        reviewService.createReview(parking.getId(), reviewPostDto, member.getEmail());
        //when
        reviewService.deleteReview(member.getEmail(), parking.getId());
        //then
        Optional<Review> byMemberEmailAndParkingId = reviewRepository.findByMemberEmailAndParkingId(member.getEmail(), parking.getId());
        assertEquals(byMemberEmailAndParkingId, Optional.empty());
    }

    @Test
    public void getAverageStar() throws Exception {
        //given
        Review review = new Review("good", 3.0, member, parking);
        reviewRepository.save(review);
        Review review2 = new Review("good2", 5.0, member2, parking);
        reviewRepository.save(review2);
        //when
        Double averageStar = reviewService.getAverageStar(parking.getId());
        //then
        assertEquals(averageStar, 4.0);
    }
}