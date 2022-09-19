package teamparkinglot.parkinggo.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.review.ReviewPostDto;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ParkingRepository parkingRepository;

    public List<Review> findReviewsByParkingOrderByCreatedDateDesc(long id) {
        return reviewRepository.findReviewsByParkingOrderByCreatedDateDesc(id);
    }

    public Review createReview(long id, ReviewPostDto reviewPostDto, String email) {

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );

        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );

        Review review = Review.builder()
                .star(reviewPostDto.getStar())
                .body(reviewPostDto.getBody())
                .member(member)
                .parking(parking)
                .build();

        System.out.println("review = " + review.getBody());

        return reviewRepository.save(review);
    }
}
