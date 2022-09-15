package teamparkinglot.parkinggo.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/api/test")
    public ResponseEntity test(@RequestBody Test test) {

        test.setTest(test.getTest() + "!!!!!");
        System.out.println("테스트 성공");
        System.out.println("test.getTest() = " + test.getTest());

        return new ResponseEntity(test, HttpStatus.OK);
    }

    @GetMapping("/")
    public String home() {
        return "안녕하세요! 접속 잘 되나요?";
    }
}
