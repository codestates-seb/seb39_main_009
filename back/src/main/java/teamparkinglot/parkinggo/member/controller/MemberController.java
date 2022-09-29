package teamparkinglot.parkinggo.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.mail.MailService;
import teamparkinglot.parkinggo.member.dto.*;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.mapper.MemberMapper;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.security.principal.PrincipalDetails;
import teamparkinglot.parkinggo.member.uuid.UuidService;
import teamparkinglot.parkinggo.member.uuid.Uuid;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;
    private final UuidService uuidService;
    private final ReservationService reservationService;

    @PostMapping("/join")
    public ResponseEntity joinUser(@Valid @RequestBody MemberJoinDto memberJoinDto) {

        Member member = mapper.memberJoinDtoToMember(memberJoinDto);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberService.memberCreate(member);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPwdSendEmail(@RequestBody @Valid ResetPwdDtoForEmail email) throws MessagingException {

        UUID uuid = UUID.randomUUID();
        Uuid saveUUID = uuidService.saveUUID(email.getEmail(), uuid);

        mailService.mailSend(email, uuid);
        uuidService.timerForDeleteIn10Min(saveUUID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/reset-password/{uuid}")
    public ResponseEntity resetPwdCheck(@PathVariable String uuid) {
        uuidService.verifyUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/reset-password/{uuid}")
    public ResponseEntity putResetPwd(@PathVariable String uuid, @RequestBody @Valid ResetPwdDto resetPwdDto) {

        uuidService.putPwd(uuid, resetPwdDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member")
    public ResponseEntity getSidebar(Authentication authentication) {

        String email = getEmail(authentication);

        SidebarDto sidebarDto = memberService.viewSidebar(email);

        return new ResponseEntity<>(sidebarDto, HttpStatus.OK);
    }

    @GetMapping("/member/reservation")
    public ResponseEntity reservationList(Authentication authentication) {

        String email = getEmail(authentication);

        List<ReservationListDto> reservationListDto = memberService.viewReservations(email);

        return new ResponseEntity<>(reservationListDto, HttpStatus.OK);
    }

    @GetMapping("/member/reservation/{id}")
    public ResponseEntity viewReservation(@PathVariable Long id) {

        ReservationResponseDto reservationResponseDto = reservationService.findByIdForReservationDto(id);

        return new ResponseEntity<>(reservationResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/member")
    public ResponseEntity edit(@RequestBody @Valid MyPagePutDto myPagePutDto,
                               Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String email = principalDetails.getUsername();

        memberService.myPageModify(myPagePutDto, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static String getEmail(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getUsername();
    }

}
