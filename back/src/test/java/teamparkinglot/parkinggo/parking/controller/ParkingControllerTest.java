package teamparkinglot.parkinggo.parking.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import teamparkinglot.parkinggo.history.service.HistoryService;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.parking.dto.*;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking.mapper.ParkingMapper;
import teamparkinglot.parkinggo.parking.repository.ParkingRepository;
import teamparkinglot.parkinggo.parking.service.ParkingService;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.reservation.service.ReservationService;
import teamparkinglot.parkinggo.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ParkingControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;
    @MockBean ParkingService parkingService;
    @MockBean ParkingMapper parkingMapper;
    @MockBean HistoryService historyService;
    @MockBean ParkingRepository parkingRepository;
    @MockBean ReservationService reservationService;

    Member member1;
    Parking parking1;

    @BeforeEach
    void set() {
        member1 = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");

        parking1 = new Parking("1", "parkingName", new Address("1", "1", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member1);
    }

    @Test
    @WithMockCustomUser
    public void viewParking() throws Exception {
        long parkingId = 1L;

        ParkingResDto parkingResDto = new ParkingResDto(parkingId, "name", "address", "weekdayOpen", "weekdayClose",
                "tel", 10, 100, 10, 100, 20, "satOpen", "satClose", "sunOpen",
                "sunClose", true, "separation", "type", "special", "info",
                "methodPay", 5000, 123.123, 456.456, "map");

        given(parkingService.findVerifiedParking(Mockito.anyLong())).willReturn(parking1);
        given(parkingMapper.parkingToParkingResDto(Mockito.any(Parking.class))).willReturn(parkingResDto);

        ResultActions actions = mockMvc.perform(
                get("/api/parking/{parkingId}", parkingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parkingId").value(parkingResDto.getParkingId()))
                .andExpect(jsonPath("$.name").value(parkingResDto.getName()))
                .andExpect(jsonPath("$.address").value(parkingResDto.getAddress()))
                .andExpect(jsonPath("$.weekdayOpen").value(parkingResDto.getWeekdayOpen()))
                .andExpect(jsonPath("$.weekdayClose").value(parkingResDto.getWeekdayClose()))
                .andExpect(jsonPath("$.tel").value(parkingResDto.getTel()))
                .andExpect(jsonPath("$.basicTime").value(parkingResDto.getBasicTime()))
                .andExpect(jsonPath("$.basicCharge").value(parkingResDto.getBasicCharge()))
                .andExpect(jsonPath("$.addUnitTime").value(parkingResDto.getAddUnitTime()))
                .andExpect(jsonPath("$.addUnitCharge").value(parkingResDto.getAddUnitCharge()))
                .andExpect(jsonPath("$.capacity").value(parkingResDto.getCapacity()))
                .andExpect(jsonPath("$.satOpen").value(parkingResDto.getSatOpen()))
                .andExpect(jsonPath("$.satClose").value(parkingResDto.getSatClose()))
                .andExpect(jsonPath("$.sunOpen").value(parkingResDto.getSunOpen()))
                .andExpect(jsonPath("$.sunClose").value(parkingResDto.getSunClose()))
                .andExpect(jsonPath("$.partnership").value(parkingResDto.getPartnership()))
                .andExpect(jsonPath("$.parkingSeparation").value(parkingResDto.getParkingSeparation()))
                .andExpect(jsonPath("$.parkingType").value(parkingResDto.getParkingType()))
                .andExpect(jsonPath("$.spacial_management").value(parkingResDto.getSpacial_management()))
                .andExpect(jsonPath("$.parkingChargeInfo").value(parkingResDto.getParkingChargeInfo()))
                .andExpect(jsonPath("$.methodPay").value(parkingResDto.getMethodPay()))
                .andExpect(jsonPath("$.dayMaxPrice").value(parkingResDto.getDayMaxPrice()))
                .andExpect(jsonPath("$.latitude").value(parkingResDto.getLatitude()))
                .andExpect(jsonPath("$.longitude").value(parkingResDto.getLongitude()))
                .andExpect(jsonPath("$.parkingMap").value(parkingResDto.getParkingMap()));
    }

    @Test
    @WithMockCustomUser
    public void parkingMap() throws Exception {
        long parkingId = 1L;
        String parkingStartDateTime = "2022-01-01 08:00:00";
        String parkingEndDateTime = "2022-01-01 09:00:00";
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

    //TODO 흐음.....
    @Test
    @WithMockCustomUser
    public void payButton() throws Exception {
        long parkingId = 1L;
        LocalDateTime start = LocalDateTime.of(2022, 1, 1, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 1, 1, 9, 0, 0);
        LocalDateTime reservationDate = LocalDateTime.of(2022, 1, 1, 7, 0, 0);
        ParkingDateTimeDto parkingDateTimeDto = new ParkingDateTimeDto(start, end, 1);
        CreateReservDto reservation = new CreateReservDto(1L, reservationDate, start, "carNumber");

        given(parkingService.createReservation(Mockito.anyLong(), Mockito.any(ParkingDateTimeDto.class), Mockito.anyString())).willReturn(reservation);

        String content = gson.toJson(parkingDateTimeDto);

        ResultActions actions = mockMvc.perform(
                post("/api/parking/{paringId}/reservation", parkingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reservNum").value(reservation.getReservNum()))
                .andExpect(jsonPath("$.reservation_date").value(reservation.getReservationDate()))
                .andExpect(jsonPath("$.parkingStartTime").value(reservation.getParkingStartTime()))
                .andExpect(jsonPath("$.carNumber").value(reservation.getCarNumber()));
    }

    @Test
    @WithMockCustomUser
    public void searchParking() throws Exception {
        ParkingCondDto parkingCondDto = new ParkingCondDto("region", "2022-01-01 08:00:00", "2022-01-01 09:00:00",
                                                            "sort", "crtLocation");
        List<Parking> parkingList = List.of(parking1);
        ParkingResDto parkingResDto = new ParkingResDto(1L, "name", "address", "weekdayOpen", "weekdayClose",
                "tel", 10, 100, 10, 100, 20, "satOpen", "satClose", "sunOpen",
                "sunClose", true, "separation", "type", "special", "info",
                "methodPay", 5000, 123.123, 456.456, "map");

        given(parkingService.findByCond(Mockito.any(ParkingCondDto.class))).willReturn(parkingList);
        given(parkingMapper.parkingToParkingResDto(Mockito.any(Parking.class))).willReturn(parkingResDto);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("region", parkingCondDto.getRegion());
        params.add("parkingStartDateTime", "2022-01-01 08:00:00");
        params.add("parkingEndDateTime", "2022-01-01 09:00:00");
        params.add("sort", parkingCondDto.getSort());
        params.add("crtLocation", parkingCondDto.getCrtLocation());

        ResultActions actions = mockMvc.perform(
                get("/api/parking")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].parkingId").value(parkingResDto.getParkingId()))
                .andExpect(jsonPath("$.[0].weekdayOpen").value(parkingResDto.getWeekdayOpen()))
                .andExpect(jsonPath("$.[0].dayMaxPrice").value(parkingResDto.getDayMaxPrice()));


    }

}