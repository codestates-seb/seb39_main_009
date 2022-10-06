package teamparkinglot.parkinggo.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.entity.Notice;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryQueryDsl {


}
