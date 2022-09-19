package teamparkinglot.parkinggo.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.repository.NoticeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public List<NoticeResDto> viewNotices() {

        List<NoticeResDto> noticeList =  noticeRepository.findNoticeAll();

        return noticeList;
    }
}
