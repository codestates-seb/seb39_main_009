package teamparkinglot.parkinggo.health_check;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.parking.service.ParkingService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final ParkingService parkingService;

    @PostMapping("/api/test")
    public ResponseEntity test(@RequestBody Test test) {

        test.setTest(test.getTest() + "!!!!!");
        log.info("테스트 성공");
        log.info("test = {}", test.getTest());

        return new ResponseEntity(test, HttpStatus.OK);
    }

    @GetMapping("/")
    public String home() {
        return "안녕하세요! 접속 잘 되나요?";
    }

    @PostMapping("/db/add")
    public ResponseEntity dbAdd(@RequestBody DbDto dbDto) {

        parkingService.saveAllItem(dbDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/db/add")
    public ResponseEntity dbPatch(@RequestBody DbDto dbDto) {
        parkingService.updateAllDb(dbDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
