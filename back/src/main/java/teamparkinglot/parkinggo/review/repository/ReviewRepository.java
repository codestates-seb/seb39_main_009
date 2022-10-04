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

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select new teamparkinglot.parkinggo.review.dto.ReviewResDto(r.id, r.member.id, r.star, r.member.nickname, r.body) from Review r where r.parking.id = :parkingId")
    Page<ReviewResDto> findReviewsByParkingOrderByCreatedDateDesc(@Param("parkingId") Long parkingId, Pageable pageable);

    @Query("select r from Review r where r.member.email = :email and r.parking.id = :parkingId")
    Optional<Review> findByMemberEmailAndParkingId(@Param("email") String email, @Param("parkingId") long parkingId);
}
