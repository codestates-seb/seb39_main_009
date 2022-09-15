package teamparkinglot.parkinggo.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("/mypage")
    public ResponseEntity viewMyPage() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/mypage")
    public ResponseEntity edit() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
