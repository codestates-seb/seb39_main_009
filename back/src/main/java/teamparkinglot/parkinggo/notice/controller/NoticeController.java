package teamparkinglot.parkinggo.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.advice.response.MultiRes;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.service.NoticeService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseEntity getNoticeList() {

        Page<NoticeResDto> noticeList = noticeService.viewNotices();

        return new ResponseEntity<>(noticeList.getContent(), HttpStatus.OK);
    }
}
