package teamparkinglot.parkinggo.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;

public interface ReviewRepositoryQueryDsl {

    Page<ReviewResDto> findReviewsByParkingOrderByCreatedDateDesc(Long parkingId, Pageable pageable);
}
