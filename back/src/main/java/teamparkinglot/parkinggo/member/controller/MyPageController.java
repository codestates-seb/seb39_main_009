package teamparkinglot.parkinggo.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.dto.MyPagePutDto;
import teamparkinglot.parkinggo.member.dto.MyPageResDto;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public ResponseEntity viewMyPage(Authentication authentication) {

        loginCheck(authentication);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String email = principalDetails.getUsername();

        MyPageResDto myPage = memberService.findMyPage(email);

        return new ResponseEntity<>(myPage, HttpStatus.OK);
    }

    @PutMapping("/mypage")
    public ResponseEntity edit(@RequestBody MyPagePutDto myPagePutDto,
                               Authentication authentication) {

        loginCheck(authentication);
        passwordCheck(myPagePutDto);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String email = principalDetails.getUsername();

        memberService.myPageModify(myPagePutDto, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void passwordCheck(MyPagePutDto myPagePutDto) {
        String password = myPagePutDto.getPassword();
        String passwordRe = myPagePutDto.getPasswordRe();
        if (!passwordRe.equals(password)) {
            throw new BusinessException(ExceptionCode.INPUT_ERROR);
        }
    }

    private static void loginCheck(Authentication authentication) {
        if (authentication == null) {
            throw new BusinessException(ExceptionCode.NEED_LOGIN);
        }
    }
}
