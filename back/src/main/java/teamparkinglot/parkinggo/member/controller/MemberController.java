package teamparkinglot.parkinggo.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.mail.MailService;
import teamparkinglot.parkinggo.member.dto.MemberJoinDto;
import teamparkinglot.parkinggo.member.dto.ResetPwdDto;
import teamparkinglot.parkinggo.member.dto.ResetPwdDtoForEmail;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.mapper.MemberMapper;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.uuid.UUIDService;
import teamparkinglot.parkinggo.uuid.Uuid;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Timer;
import java.util.TimerTask;
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
    private final UUIDService uuidService;

    @PostMapping("/join")
    public ResponseEntity joinUser(@Valid @RequestBody MemberJoinDto memberJoinDto) {

        Member member = mapper.memberJoinDtoToMember(memberJoinDto);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberService.memberCreate(member);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/resetpwd")
    public ResponseEntity resetPwdSendEmail(@RequestBody @Valid ResetPwdDtoForEmail email) throws MessagingException {

        UUID uuid = UUID.randomUUID();
        Uuid saveUUID = uuidService.saveUUID(email.getEmail(), uuid);

        mailService.mailSend(email, uuid);

        timerForDeleteIn10Min(saveUUID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void timerForDeleteIn10Min(Uuid saveUUID) {
        log.info("타이머 시작");
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int count = 0;
            @Override
            public void run() {
                log.info("삭제 로직 시작");
                if (count++ < 1) uuidService.delete(saveUUID);
                else timer.cancel();
            }
        };
        timer.schedule(timerTask, 600000);
    }

    @GetMapping("/resetpwd/{UUID}")
    public ResponseEntity resetPwdCheck(@PathVariable String UUID) {
        uuidService.verifyUuid(UUID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resetpwd/{UUID}")
    public ResponseEntity putResetPwd(@PathVariable String UUID, @RequestBody ResetPwdDto resetPwdDto) {

        if (resetPwdDto.getPassword() != resetPwdDto.getPasswordRe()) {
            throw new BusinessException(ExceptionCode.INPUT_ERROR);
        }

        uuidService.putPwd(UUID, resetPwdDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
