package teamparkinglot.parkinggo.review.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryQueryDsl {

    @Query("select r from Review r where r.member.email = :email and r.parking.id = :parkingId")
    Optional<Review> findByMemberEmailAndParkingId(@Param("email") String email, @Param("parkingId") long parkingId);
    List<Review> findByParkingId(long id);

    @Query("select r from Review r join fetch r.member where r.id = :reviewId")
    Optional<Review> findByReviewId(@Param("reviewId") long reviewId);
}
