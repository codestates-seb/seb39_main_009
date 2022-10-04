package teamparkinglot.parkinggo.bookmark.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkIdDto;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkResDto;
import teamparkinglot.parkinggo.bookmark.dto.BookmarkStatusDto;
import teamparkinglot.parkinggo.bookmark.entity.Bookmark;
import teamparkinglot.parkinggo.bookmark.service.BookmarkService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookmarkController.class)
@MockBean(JpaMetamodelMappingContext.class)
class BookmarkControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;
    @MockBean BookmarkService bookmarkService;

    @Test
    @WithMockCustomUser
    public void postBookmark() throws Exception {

        BookmarkIdDto bookmarkIdDto = new BookmarkIdDto(1L);

        doNothing().when(bookmarkService).saveBookmark(Mockito.anyString(), Mockito.anyLong());

        String content = gson.toJson(bookmarkIdDto);

        ResultActions actions = mockMvc.perform(
                post("/api/bookmark")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void deleteBookmark() throws Exception {

        long parkingId = 1L;

        doNothing().when(bookmarkService).deleteBookmark(Mockito.anyString(), Mockito.anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/api/bookmark/{parkingId}", parkingId)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void getBookmarkList() throws Exception {

        List<BookmarkResDto> bookmarkResDto = List.of(new BookmarkResDto(1L, "name", "address"));

        given(bookmarkService.getBookmarkList(Mockito.anyString())).willReturn(bookmarkResDto);

        ResultActions actions = mockMvc.perform(
                get("/api/bookmark")
                        .with(csrf())
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].parkingId").value(bookmarkResDto.get(0).getParkingId()))
                .andExpect(jsonPath("$.[0].name").value(bookmarkResDto.get(0).getName()))
                .andExpect(jsonPath("$.[0].address").value(bookmarkResDto.get(0).getAddress()));
    }

    @Test
    @WithMockCustomUser
    public void getBookmarkStatus() throws Exception {

        long parkingId = 1L;
        BookmarkStatusDto bookmarkStatusDto = new BookmarkStatusDto();

        given(bookmarkService.checkBookmarkStatus(Mockito.anyString(), Mockito.anyLong())).willReturn(bookmarkStatusDto);

        ResultActions actions = mockMvc.perform(
                get("/api/bookmarkCheck/{parkingId}", parkingId)
                        .with(csrf())
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookmark").value(bookmarkStatusDto.isBookmark()));
    }
}