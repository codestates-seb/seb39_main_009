package teamparkinglot.parkinggo.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member memberCreate(Member member) {

        verifyMember(member);

        return memberRepository.save(member);
    }

    @Transactional
    public void setRefreshToken(String refreshToken, String email) {

        Member member = findVerifiedMember(email);
        member.setRefreshToken(refreshToken);

    }

    private void verifyMember(Member member) {
        memberRepository.findByEmail(member.getEmail()).ifPresent(
                e -> new BusinessException(ExceptionCode.MEMBER_EXISTS)
        );
    }

    private Member findVerifiedMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );
    }

}
