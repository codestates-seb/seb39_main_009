package teamparkinglot.parkinggo.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.mapper.ReviewMapper;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.service.ReviewService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;
import teamparkinglot.parkinggo.advice.response.MultiRes;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @GetMapping("/reviews/{parkingId}")
    public ResponseEntity viewReviews(@PathVariable long parkingId,
                                      @RequestParam("page") int page) {

        Page<ReviewResDto> reviewsPage = reviewService.findReviewsByParkingOrderByCreatedDateDesc(parkingId, page);

        return new ResponseEntity<>(new MultiRes<>(reviewsPage.getContent(), reviewsPage), HttpStatus.OK);
    }

    @PostMapping("/reviews/{parkingId}")
    public ResponseEntity postReview(@PathVariable long parkingId,
                                     @RequestBody ReviewPostDto reviewPostDto,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        reviewService.createReview(parkingId, reviewPostDto, principalDetails.getUsername());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/reviews/{parkingId}/{reviewId}")
    public ResponseEntity viewReviewForPatch(@PathVariable("parkingId") long parkingId,
                                             @PathVariable("reviewId") long reviewId) {

        Review review = reviewService.findReview(reviewId);
        ReviewResDto reviewResDto = mapper.reviewsToReviewsResDto(review);

        return new ResponseEntity<>(reviewResDto, HttpStatus.OK);
    }

    @PatchMapping("/reviews/{parkingId}")
    public ResponseEntity patchReview(@PathVariable long parkingId,
                                      @RequestBody @Valid ReviewPostDto reviewPostDto,
                                      @AuthenticationPrincipal PrincipalDetails principalDetails) {

        reviewService.editReview(principalDetails.getUsername(), parkingId, reviewPostDto.getStar(), reviewPostDto.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{parkingId}")
    public ResponseEntity deleteReview(@PathVariable long parkingId,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {

        reviewService.deleteReview(principalDetails.getUsername(), parkingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
