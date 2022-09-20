package teamparkinglot.parkinggo.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.member.dto.MemberJoinDto;
import teamparkinglot.parkinggo.member.dto.MemberLoginDto;
import teamparkinglot.parkinggo.member.dto.ResetPwdDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.mapper.MemberMapper;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.secret.SecretCode;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    private final SecretCode secretCode;
    private String from = "파킹Go";

    @PostMapping("/join")
    public ResponseEntity joinUser(@Valid @RequestBody MemberJoinDto memberJoinDto) {

        Member member = mapper.memberJoinDtoToMember(memberJoinDto);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberService.memberCreate(member);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/resetpwd")
    public ResponseEntity resetPwdSendEmail(@RequestBody String email) throws MessagingException {

        UUID uuid = UUID.randomUUID();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("[파킹Go] 비밀번호 재설정 주소 안내");

        StringBuilder sb = new StringBuilder();
        sb.append("비밀번호 재설정을 위한 주소입니다. \n");
        sb.append(secretCode.getClientUrl() + "/resetpwd" + uuid + "\n");
        sb.append("상기 주소로 접속하시어 비밀번호 재설정을 해주시기 바랍니다.");

        mimeMessageHelper.setText(sb.toString(), true);

        javaMailSender.send(mimeMessage);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/resetpwd/{UUID}")
    public ResponseEntity resetPwdCheck(@PathVariable String UUID) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resetpwd/{UUID}")
    public ResponseEntity putResetPwd(@PathVariable String UUID, String password) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
