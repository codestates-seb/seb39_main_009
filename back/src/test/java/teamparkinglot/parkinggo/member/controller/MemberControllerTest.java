package teamparkinglot.parkinggo.member.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.member.dto.*;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.mail.MailService;
import teamparkinglot.parkinggo.member.mapper.MemberMapper;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.member.uuid.Uuid;
import teamparkinglot.parkinggo.member.uuid.UuidService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("test")
class MemberControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private Gson gson;
    @MockBean private MemberService memberService;
    @MockBean private MemberMapper mapper;
    @MockBean private ReservationService reservationService;
    @MockBean private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean private MailService mailService;
    @MockBean private UuidService uuidService;

    @Test
    @WithMockCustomUser
    public void joinUser() throws Exception {

        MemberJoinDto memberJoinDto = new MemberJoinDto("email@email.com", "password1234!", "010-1010-0101", "name", "11가1111",
                true, true, true);

        Member member = new Member("email1", "password1", "nickname1", MemberRole.USER, null, "carNumber1",
                "phone1", 10000L, true, true, true, "provider", "providerId");

        given(mapper.memberJoinDtoToMember(Mockito.any(MemberJoinDto.class))).willReturn(member);
        given(memberService.memberCreate(Mockito.any(Member.class))).willReturn(member);

        String content = gson.toJson(memberJoinDto);

        ResultActions actions = mockMvc.perform(
                post("/api/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isCreated());
    }

    @Test
    @WithMockCustomUser
    public void resetPwdSendEmail() throws Exception {

        ResetPwdDtoForEmail resetPwdDtoForEmail = new ResetPwdDtoForEmail("email@email.com");
        doNothing().when(mailService).mailSend(Mockito.any(ResetPwdDtoForEmail.class), Mockito.any(UUID.class));
        doNothing().when(uuidService).timerForDeleteIn10Min(Mockito.any(Uuid.class));

        String content = gson.toJson(resetPwdDtoForEmail);

        ResultActions actions = mockMvc.perform(
                post("/api/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());

    }

    @Test
    @WithMockCustomUser
    public void resetPwdCheck() throws Exception {

        String uuid = "uuid";
        given(uuidService.verifyUuid(Mockito.anyString())).willReturn(new Uuid(Mockito.any(Member.class), uuid));

        String content = gson.toJson(uuid);

        ResultActions actions = mockMvc.perform(
                get("/api/reset-password/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void putResetPwd() throws Exception {
        String uuid = "uuid";
        ResetPwdDto resetPwdDto = new ResetPwdDto("abcd1234!");

        doNothing().when(uuidService).putPwd(Mockito.anyString(), Mockito.anyString());

        String content = gson.toJson(resetPwdDto);

        ResultActions actions = mockMvc.perform(
                patch("/api/reset-password/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void getSidebar() throws Exception {

        SidebarDto sidebarDto = new SidebarDto("name", 1L, "email", 1, 1000L, "phone", "carNum");

        given(memberService.viewSidebar(Mockito.anyString())).willReturn(sidebarDto);

        ResultActions actions = mockMvc.perform(
                get("/api/member")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(sidebarDto.getName()))
                .andExpect(jsonPath("$.point").value(sidebarDto.getPoint()));
    }



    //TODO valid 에러남
    @Test
    @WithMockCustomUser
    public void edit() throws Exception {

        MyPagePutDto myPagePutDto = new MyPagePutDto("abcd1245!", "010-1234-6245", "23구9345");

        doNothing().when(memberService).myPageModify(Mockito.any(MyPagePutDto.class), Mockito.anyString());

        String content = gson.toJson(myPagePutDto);

        ResultActions actions = mockMvc.perform(
                patch("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
        );

        actions.andExpect(status().isOk());

    }

}