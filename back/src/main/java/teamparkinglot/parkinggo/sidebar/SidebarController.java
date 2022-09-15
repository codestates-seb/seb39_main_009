package teamparkinglot.parkinggo.sidebar;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SidebarController {

    @GetMapping("/sidebar")
    public ResponseEntity viewSidebar() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sidebar/reservation")
    public ResponseEntity reservationList() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sidebar/bookmark")
    public ResponseEntity bookmarkList() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
