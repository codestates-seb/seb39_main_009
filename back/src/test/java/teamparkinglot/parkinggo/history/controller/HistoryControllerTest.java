package teamparkinglot.parkinggo.history.controller;

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
import teamparkinglot.parkinggo.history.service.HistoryService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.parking.dto.ParkingRecentDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
@MockBean(JpaMetamodelMappingContext.class)
class HistoryControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private HistoryService historyService;

    @Test
    @WithMockCustomUser
    public void searchHistory() throws Exception {

        List<ParkingRecentDto> parkingRecentDto = List.of(new ParkingRecentDto(1L, "parkingName", "address"));

        given(historyService.findRecentSearches(Mockito.anyString())).willReturn(parkingRecentDto);

        ResultActions actions = mockMvc.perform(
                get("/api/history")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].parkingId").value(parkingRecentDto.get(0).getParkingId()))
                .andExpect(jsonPath("$.[0].parkingName").value(parkingRecentDto.get(0).getParkingName()))
                .andExpect(jsonPath("$.[0].address").value(parkingRecentDto.get(0).getAddress()));
    }
}