package teamparkinglot.parkinggo.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController {

    @GetMapping("/notice")
    public ResponseEntity viewNoticeList() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
