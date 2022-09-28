package teamparkinglot.parkinggo.review.service;

import lombok.RequiredArgsConstructor;
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

        checkExistReview(member.getId(), parking.getId());

        Review review = Review.builder()
                .star(reviewPostDto.getStar())
                .body(reviewPostDto.getBody())
                .member(member)
                .parking(parking)
                .build();

        return reviewRepository.save(review);
    }

    private void checkExistReview(Long memberId, Long parkingId) {
        Optional<Review> review = reviewRepository.findByMemberIdAndParkingId(memberId, parkingId);
        if (review.isPresent()) throw new BusinessException(ExceptionCode.REVIEW_EXISTS);
    }
}
