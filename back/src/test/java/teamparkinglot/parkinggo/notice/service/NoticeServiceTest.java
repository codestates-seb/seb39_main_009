package teamparkinglot.parkinggo.notice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        //when
        Page<NoticeResDto> noticeAll = noticeRepository.findNoticeAll(pageable);
        //then
        assertEquals(noticeAll.getContent().get(0).getTitle(), notice.getTitle());
    }

}