package teamparkinglot.parkinggo.review.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.parking.entity.Address;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.parking_place.ParkingPlace;
import teamparkinglot.parkinggo.reservation.entity.Reservation;
import teamparkinglot.parkinggo.review.dto.ReviewPostDto;
import teamparkinglot.parkinggo.review.dto.ReviewResDto;
import teamparkinglot.parkinggo.review.entity.Review;
import teamparkinglot.parkinggo.review.mapper.ReviewMapper;
import teamparkinglot.parkinggo.review.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ReviewControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;
    @MockBean ReviewService reviewService;
    @MockBean ReviewMapper reviewMapper;
    Member member1;
    Parking parking1;
    Review review1;

    Page<ReviewResDto> reviewResDtoPage;



    @BeforeEach
    void set() {
        member1 = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");

        parking1 = new Parking("1", "parkingName", new Address("1", "1", "1"), "weekOpen",
                "weekClose", "tel", 10, 100, 10, 100, 10, "satOpen", "satClose",
                "sunOpen", "sunClose", true, "separation", "type", "specialM", "info",
                "methodPay", 5000, 195.143, 153.153, "map", "phone", member1);

        review1 = new Review("body1", 1.0, member1, parking1);
        Pageable pageable = PageRequest.of(0, 5);
        ReviewResDto reviewResDto = new ReviewResDto(1L, 1L, 1.0, review1.getMember().getNickname(), review1.getBody());

        reviewResDtoPage = new PageImpl(List.of(reviewResDto), pageable, 1);
    }


    @Test
    @WithMockCustomUser
    public void viewReviews() throws Exception {

        ReviewResDto reviewResDto1 = new ReviewResDto(1L, 1L, 1.0,"nickname1", "body1");

        given(reviewService.findReviewsByParkingOrderByCreatedDateDesc(Mockito.anyLong(), Mockito.anyInt())).willReturn(reviewResDtoPage);
        given(reviewMapper.reviewsToReviewsResDto(Mockito.any(Review.class))).willReturn(reviewResDto1);

        Long parkingId = 1L;

        ResultActions actions = mockMvc.perform(
                get("/api/reviews/{parkingId}?page=1", parkingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.[0].reviewId").value(reviewResDto1.getReviewId()))
                .andExpect(jsonPath("$.data.[0].memberId").value(reviewResDto1.getMemberId()))
                .andExpect(jsonPath("$.data.[0].nickName").value(reviewResDto1.getNickName()))
                .andExpect(jsonPath("$.data.[0].body").value(reviewResDto1.getBody()));

    }

    @Test
    @WithMockCustomUser
    public void postReview() throws Exception {
        ReviewPostDto reviewPostDto = new ReviewPostDto(1.0, "body1");


        long parkingId = 1L;
        String content = gson.toJson(reviewPostDto);

        ResultActions actions = mockMvc.perform(
                post("/api/reviews/{parkingId}", parkingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(content)
        );

        actions.andExpect(status().isCreated());

    }

    @Test
    @WithMockCustomUser
    public void patchReview() throws Exception {

        ReviewPostDto reviewPostDto = new ReviewPostDto(2.0, "body2");

        long parkingId = 1L;
        String content = gson.toJson(reviewPostDto);

        ResultActions actions = mockMvc.perform(
                patch("/api/reviews/{parkingId}", parkingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(content)
        );

        actions.andExpect(status().isOk());

    }

    @Test
    @WithMockCustomUser
    public void deleteReview() throws Exception {

        long parkingId = 1L;

        ResultActions actions = mockMvc.perform(
                delete("/api/reviews/{parkingId}", parkingId)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }


}