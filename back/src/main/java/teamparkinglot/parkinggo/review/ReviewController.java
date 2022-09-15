package teamparkinglot.parkinggo.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping("/parking/{id}/review")
    public ResponseEntity viewReviews(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/parking/{id}/review")
    public ResponseEntity postReview(@PathVariable long id, ReviewPostDto reviewPostDto) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
