package teamparkinglot.parkinggo.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;

public interface NoticeRepositoryQueryDsl {

    Page<NoticeResDto> findNoticeAll(Pageable pageable);
}
