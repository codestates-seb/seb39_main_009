package teamparkinglot.parkinggo.reservation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import teamparkinglot.parkinggo.member.dto.ReservationListDto;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.parking.dto.ParkingMapDto;
import teamparkinglot.parkinggo.parking.dto.SelectTimeDto;
import teamparkinglot.parkinggo.parking.dto.ValidNum;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("test")
class ReservationControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private ReservationService reservationService;
    @MockBean private MemberService memberService;
    @MockBean private ParkingService parkingService;

    @Test
    @WithMockCustomUser
    public void finalPay() throws Exception {

        long reservationId = 1L;

        doNothing().when(reservationService).finalPayment(Mockito.anyLong());

        ResultActions actions = mockMvc.perform(
                post("/api/pay/{reservationId}", reservationId)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void cancelPay() throws Exception {

        long reservationId = 1L;

        doNothing().when(reservationService).cancelPayment(Mockito.anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/api/pay/{reservationId}", reservationId)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void viewReservation() throws Exception {
        long parkingId = 1L;
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto(
                "email", "phone", 1L, "parkingName", 1,
                LocalDateTime.of(2022, 11, 11, 10, 10, 0),
                LocalDateTime.of(2022, 11, 11, 11, 10, 0), 1000L, parkingId);

        given(reservationService.findByIdForReservationDto(Mockito.anyLong())).willReturn(reservationResponseDto);

        ResultActions actions = mockMvc.perform(
                get("/api/member/reservation/{id}", parkingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(reservationResponseDto.getEmail()))
                .andExpect(jsonPath("$.reservationId").value(reservationResponseDto.getReservationId()))
                .andExpect(jsonPath("$.price").value(reservationResponseDto.getPrice()));
    }

    @Test
    @WithMockCustomUser
    public void reservationList() throws Exception {
        ReservationListDto reservationListDto = new ReservationListDto("name", 1, 1L,
                LocalDateTime.of(2022, 11, 11, 10, 10, 0),
                LocalDateTime.of(2022, 11, 11, 11, 10, 0));
        List<ReservationListDto> reservationListDtoList = List.of(reservationListDto);

        given(memberService.viewReservations(Mockito.anyString())).willReturn(reservationListDtoList);

        ResultActions actions = mockMvc.perform(
                get("/api/member/reservation")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value(reservationListDto.getName()));
    }

    @Test
    @WithMockCustomUser
    public void parkingMap() throws Exception {
        long parkingId = 1L;
        String parkingStartDateTime = "2022-01-01 08:00";
        String parkingEndDateTime = "2022-01-01 09:00";
        SelectTimeDto selectTimeDto = new SelectTimeDto(parkingStartDateTime, parkingEndDateTime);
        ParkingMapDto map = new ParkingMapDto();
        map.setImageURL("url");
        map.setValidNum(List.of(new ValidNum(1)));
        given(parkingService.findMap(parkingId, selectTimeDto)).willReturn(map);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("parkingStartDateTime", parkingStartDateTime);
        multiValueMap.add("parkingEndDateTime", parkingEndDateTime);

        ResultActions actions = mockMvc.perform(
                get("/api/parking/{parkingId}/reservation", parkingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(multiValueMap)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageURL").value(map.getImageURL()))
                .andExpect(jsonPath("$.validNum[0].number").value(map.getValidNum().get(0).getNumber()));
    }
}