package teamparkinglot.parkinggo.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    @PostMapping("/bookmark")
    public ResponseEntity postBookmark(long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/bookmark")
    public ResponseEntity deleteBookmark(long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
