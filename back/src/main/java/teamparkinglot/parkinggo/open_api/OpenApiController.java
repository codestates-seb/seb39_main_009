package teamparkinglot.parkinggo.open_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/hihi")
@RequiredArgsConstructor
public class OpenApiController {

    private final ApiExplorer apiExplorer;

    @PostMapping
    public ResponseEntity test(@RequestBody Hi hi) {


        try {
            apiExplorer.searchFromApi(hi.test);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>("되긴 되냐", HttpStatus.OK);
    }

}
