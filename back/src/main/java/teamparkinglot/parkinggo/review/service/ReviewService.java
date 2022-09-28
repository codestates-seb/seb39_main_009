package teamparkinglot.parkinggo.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ParkingRepository parkingRepository;

    public List<Review> findReviewsByParkingOrderByCreatedDateDesc(long parkingId) {
        return reviewRepository.findReviewsByParkingOrderByCreatedDateDesc(parkingId);
    }

    @Transactional
    public Review createReview(long parkingId, ReviewPostDto reviewPostDto, String email) {

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );

        Parking parking = parkingRepository.findById(parkingId).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );

        checkExistReview(email, parking.getId());

        Review review = Review.builder()
                .star(reviewPostDto.getStar())
                .body(reviewPostDto.getBody())
                .member(member)
                .parking(parking)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public void editReview(String email, long parkingId, Double star, String body) {
        Review review = reviewRepository.findByMemberEmailAndParkingId(email, parkingId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.REVIEW_NOT_EXISTS));

        review.setStar(star);
        review.setBody(body);
    }

    @Transactional
    public void deleteReview(String email, long parkingId) {
        Review review = reviewRepository.findByMemberEmailAndParkingId(email, parkingId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.REVIEW_NOT_EXISTS));

        reviewRepository.delete(review);
    }

    private void checkExistReview(String email, Long parkingId) {
        Optional<Review> review = reviewRepository.findByMemberEmailAndParkingId(email, parkingId);
        if (review.isPresent()) throw new BusinessException(ExceptionCode.REVIEW_EXISTS);
    }
}
