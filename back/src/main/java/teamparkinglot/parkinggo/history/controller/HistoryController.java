package teamparkinglot.parkinggo.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.history.service.HistoryService;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;


    @GetMapping("/history")
    public ResponseEntity searchHistory(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String email = null;

        if (principalDetails != null) {
            email = principalDetails.getUsername();
        }

        List<ParkingRecentDto> recentSearches = historyService.findRecentSearches(email);

        return new ResponseEntity<>(recentSearches, HttpStatus.OK);
    }
}
