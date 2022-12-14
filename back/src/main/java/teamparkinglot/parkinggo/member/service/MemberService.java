package teamparkinglot.parkinggo.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.member.dto.MyPagePutDto;
import teamparkinglot.parkinggo.member.dto.MyPageResDto;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.member.dto.SidebarDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepositoryQueryDsl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Member memberCreate(Member member) {

        verifyMember(member);
        member.setPoint(100000L);

        return memberRepository.save(member);
    }

    @Transactional
    public void myPageModify(MyPagePutDto myPagePutDto, String email) {

        Member findMember = findVerifiedMember(email);

        String password = myPagePutDto.getPassword();
        String phoneNum = myPagePutDto.getPhoneNum();
        String carNumber = myPagePutDto.getCarNumber();

        if (password != null) {
            findMember.setPassword(bCryptPasswordEncoder.encode(password));
        }

        if (phoneNum != null) {
            findMember.setPhone(phoneNum);
        }

        if (carNumber != null) {
            findMember.setCarNumber(carNumber);
        }
    }

    public SidebarDto viewSidebar(String email) {

        return memberRepository.findSidebar(email);
    }

    public List<ReservationListDto> viewReservations(String email) {

        List<ReservationListDto> reservationList = reservationRepository.findReservationList(email);
        reservationList = reservationList.stream()
                .map(e -> {
                    e.setParkingEndTime(e.getParkingEndTime().plusSeconds(1));
                    return e;
                }).collect(Collectors.toList());
        return reservationList;
    }

    @Transactional
    public void changeCarNumber(String email, String carNumber) {

        Member member = findVerifiedMember(email);
        member.setCarNumber(carNumber);

    }

    private void verifyMember(Member member) {
        Optional<Member> find = memberRepository.findByEmail(member.getEmail());
        if (find.isPresent()) {
            throw new BusinessException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public Member findVerifiedMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_EXISTS)
        );
    }
}
