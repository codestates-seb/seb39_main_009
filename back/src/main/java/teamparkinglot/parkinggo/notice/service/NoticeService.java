package teamparkinglot.parkinggo.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<NoticeResDto> viewNotices() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<NoticeResDto> noticeList =  noticeRepository.findNoticeAll(pageable);

        return noticeList;
    }
}
