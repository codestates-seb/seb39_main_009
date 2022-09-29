package teamparkinglot.parkinggo.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.mapper.ReviewMapper;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.service.ReviewService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;
import teamparkinglot.parkinggo.util.MultiRes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @GetMapping("/reviews/{parkingId}")
    public ResponseEntity viewReviews(@PathVariable long parkingId) {

        System.out.println("리뷰 조회!!!!!!!!!!!" );

        List<Review> reviews = reviewService.findReviewsByParkingOrderByCreatedDateDesc(parkingId);
        List<ReviewResDto> collect = reviews.stream()
                .map(e -> mapper.reviewsToReviewsResDto(e))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new MultiRes<>(collect), HttpStatus.OK);
    }

    @PostMapping("/reviews/{parkingId}")
    public ResponseEntity postReview(@PathVariable long parkingId,
                                     @RequestBody ReviewPostDto reviewPostDto,
                                     Authentication authentication) {

        System.out.println("리뷰 작성!!!!!!!!");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        reviewService.createReview(parkingId, reviewPostDto, principal.getUsername());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{parkingId}")
    public ResponseEntity patchReview(@PathVariable long parkingId,
                                      @RequestBody @Valid ReviewPostDto reviewPostDto,
                                      Authentication authentication) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        reviewService.editReview(principal.getUsername(), parkingId, reviewPostDto.getStar(), reviewPostDto.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{parkingId}")
    public ResponseEntity deleteReview(@PathVariable long parkingId,
                                       Authentication authentication) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        reviewService.deleteReview(principal.getUsername(), parkingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
