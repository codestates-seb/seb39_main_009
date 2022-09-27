package teamparkinglot.parkinggo.uuid;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.service.MemberService;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UuidService {

    private final UuidRepository uuidRepository;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Uuid saveUUID(String email, UUID UUID) {

        alreadyUuidExistsCheck(email);

        Member member = memberService.findVerifiedMember(email);
        Uuid uuid = new Uuid(member, UUID.toString());


        return uuidRepository.save(uuid);
    }

    @Async
    @Transactional
    public void timerForDeleteIn10Min(Uuid saveUuid) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int count = 0;
            @Override
            public void run() {
                if (count++ < 1) uuidRepository.delete(saveUuid);
                else timer.cancel();
            }
        };
        timer.schedule(timerTask, 600000);
    }

    private void alreadyUuidExistsCheck(String email) {
        Optional<Uuid> byEmail = uuidRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            uuidRepository.delete(byEmail.get());
        }
    }

    public Uuid verifyUuid(String uuid) {
        return uuidRepository.findByUuid(uuid).orElseThrow(
                () -> new BusinessException(ExceptionCode.UUID_NOT_MATCH)
        );
    }

    @Transactional
    public void putPwd(String uuid, String password) {

        Uuid findUuid = verifyUuid(uuid);

        Member member = findUuid.getMember();
        member.setPassword(bCryptPasswordEncoder.encode(password));
    }
}
