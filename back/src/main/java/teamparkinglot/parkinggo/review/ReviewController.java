package teamparkinglot.parkinggo.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping("/parking/{id}/review")
    public ResponseEntity viewReview(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
