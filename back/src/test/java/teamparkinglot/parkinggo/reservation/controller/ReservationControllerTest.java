package teamparkinglot.parkinggo.reservation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ReservationControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private ReservationService reservationService;

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
}