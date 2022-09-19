package teamparkinglot.parkinggo.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.review.ReviewPostDto;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.mapper.ReviewMapper;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.repository.ReviewRepository;
import teamparkinglot.parkinggo.review.service.ReviewService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;
import teamparkinglot.parkinggo.util.MultiRes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @GetMapping("/parking/{id}/review")
    public ResponseEntity viewReviews(@PathVariable long id) {

        List<Review> reviews = reviewService.findReviewsByParkingOrderByCreatedDateDesc(id);
        List<ReviewResDto> collect = reviews.stream()
                .map(e -> mapper.reviewsToReviewsResDto(e))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new MultiRes<>(collect), HttpStatus.OK);
    }

    @PostMapping("/parking/{id}/review")
    public ResponseEntity postReview(@PathVariable long id,
                                     @RequestBody ReviewPostDto reviewPostDto,
                                     Authentication authentication) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        reviewService.createReview(id, reviewPostDto, principal.getUsername());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
