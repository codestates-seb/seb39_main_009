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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import teamparkinglot.parkinggo.member.dto.MemberJoinDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;
import teamparkinglot.parkinggo.member.mail.MailService;
import teamparkinglot.parkinggo.member.mapper.MemberMapper;
import teamparkinglot.parkinggo.member.service.MemberService;
import teamparkinglot.parkinggo.member.uuid.UuidService;
import teamparkinglot.parkinggo.mock_custom_user.WithMockCustomUser;
import teamparkinglot.parkinggo.reservation.service.ReservationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
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

        given(mapper.memberJoinDtoToMember(memberJoinDto)).willReturn(member);
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

}