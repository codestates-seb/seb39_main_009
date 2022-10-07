package teamparkinglot.parkinggo.notice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.notice.dto.NoticeResDto;
import teamparkinglot.parkinggo.notice.entity.NoticeType;
import teamparkinglot.parkinggo.notice.service.NoticeService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoticeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("test")
class NoticeControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private NoticeService noticeService;

    @Test
    @WithMockCustomUser
    public void getNoticeList() throws Exception {

        List<NoticeResDto> noticeResDto = List.of(new NoticeResDto(NoticeType.EVENT, "title", "body", LocalDateTime.MAX, LocalDateTime.MAX));

        given(noticeService.viewNotices()).willReturn(noticeResDto);

        ResultActions actions = mockMvc.perform(
                get("/api/notice")
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].type").value(noticeResDto.get(0).getType().toString()))
                .andExpect(jsonPath("$.[0].title").value(noticeResDto.get(0).getTitle()))
                .andExpect(jsonPath("$.[0].body").value(noticeResDto.get(0).getBody()))
                .andExpect(jsonPath("$.[0].createdDate").value(noticeResDto.get(0).getCreatedDate().toString()));
    }

}