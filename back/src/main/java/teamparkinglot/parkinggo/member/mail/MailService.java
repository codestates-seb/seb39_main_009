package teamparkinglot.parkinggo.member.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.member.dto.ResetPwdDtoForEmail;
import teamparkinglot.parkinggo.config.secret.SecretCode;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final SecretCode secretCode;
    private String from = "kwj1830@gmail.com";

    @Async
    public void mailSend(ResetPwdDtoForEmail email, UUID uuid) throws MessagingException {
        log.info("비밀번호 재설정을 위한 메일 발송 로직 진입");
        log.info("보낼 메일 주소 : {}", email);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email.getEmail());
        mimeMessageHelper.setSubject("[파킹Go] 비밀번호 재설정 주소 안내");

        StringBuilder sb = new StringBuilder();
        sb.append("비밀번호 재설정을 위한 주소입니다. \n");
        sb.append(secretCode.getClientUrl() + "/resetpwd/" + uuid + "\n");
        sb.append("상기 주소로 접속하시어 비밀번호 재설정을 해주시기 바랍니다.");
        sb.append("해당 주소는 약 10분간 유효한 주소 입니다.");

        mimeMessageHelper.setText(sb.toString());
        javaMailSender.send(mimeMessage);
        log.info("메일 보내기 완료!");
    }

}
