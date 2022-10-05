package teamparkinglot.parkinggo.notice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.entity.Notice;
import teamparkinglot.parkinggo.notice.entity.NoticeType;
import teamparkinglot.parkinggo.notice.repository.NoticeRepository;
import teamparkinglot.parkinggo.reservation.repository.ReservationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class NoticeServiceTest {
    @Autowired private NoticeRepository noticeRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    public void viewNotices() throws Exception {
        //given
        Member member = new Member("email1", "password1", "nickname1", MemberRole.ADMIN, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");
        memberRepository.save(member);
        Notice notice = new Notice("title", "body", false, NoticeType.NORMAL, member);
        noticeRepository.save(notice);
        //when
        List<NoticeResDto> noticeAll = noticeRepository.findNoticeAll();
        //then
        assertEquals(noticeAll.get(0).getTitle(), notice.getTitle());
    }

}