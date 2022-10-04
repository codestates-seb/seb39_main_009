package teamparkinglot.parkinggo.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ParkingRepository parkingRepository;
    private final ReservationRepository reservationRepository;

    public Page<ReviewResDto> findReviewsByParkingOrderByCreatedDateDesc(long parkingId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id").descending());
        return reviewRepository.findReviewsByParkingOrderByCreatedDateDesc(parkingId, pageable);
    }

    @Transactional
    public void createReview(long parkingId, ReviewPostDto reviewPostDto, String email) {


        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );

        Parking parking = parkingRepository.findById(parkingId).orElseThrow(
                () -> new BusinessException(ExceptionCode.PARKING_NOT_EXISTS)
        );
        if(!reservationRepository.findByParkingAndMember(parkingId, member.getId()).isEmpty()) {

            checkExistReview(email, parking.getId());

            Review review = Review.builder()
                    .star(reviewPostDto.getStar())
                    .body(reviewPostDto.getBody())
                    .member(member)
                    .parking(parking)
                    .build();

            reviewRepository.save(review);
        } else throw new BusinessException(ExceptionCode.RESERVATION_NOT_EXISTS);
    }

    @Transactional
    public void editReview(String email, long parkingId, Double star, String body) {
        Review review = reviewRepository.findByMemberEmailAndParkingId(email, parkingId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.REVIEW_NOT_EXISTS));

        review.editReview(star, body);
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

    public Double getAverageStar(long id) {

        OptionalDouble average = reviewRepository.findByParkingId(id).stream()
                .map(e -> e.getStar())
                .mapToDouble(e -> Double.valueOf(e))
                .average();

        if (average.isEmpty()) {
            return 0.0;
        }

        return average.getAsDouble();
    }

    public Review findReview(long reviewId) {

        return reviewRepository.findByReviewId(reviewId).orElseThrow(
                () -> new BusinessException(ExceptionCode.REVIEW_NOT_EXISTS)
        );
    }
}
