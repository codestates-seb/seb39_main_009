package teamparkinglot.parkinggo.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.member.dto.SidebarDto;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SidebarController {

    private final MemberService memberService;

    @GetMapping("/sidebar")
    public ResponseEntity getSidebar(Authentication authentication) {
        loginCheck(authentication);

        String email = getEmail(authentication);

        SidebarDto sidebarDto = memberService.viewSidebar(email);

        return new ResponseEntity<>(sidebarDto, HttpStatus.OK);
    }

    @GetMapping("/sidebar/reservation")
    public ResponseEntity reservationList(Authentication authentication) {
        loginCheck(authentication);

        String email = getEmail(authentication);

        List<ReservationListDto> reservationListDto = memberService.viewReservations(email);

        return new ResponseEntity<>(reservationListDto, HttpStatus.OK);
    }

    private static void loginCheck(Authentication authentication) {
        if (authentication == null) {
            throw new BusinessException(ExceptionCode.NEED_LOGIN);
        }
    }

    private static String getEmail(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getUsername();
    }
}
