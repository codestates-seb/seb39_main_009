package teamparkinglot.parkinggo.member.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.member.dto.MemberJoinDto;
import teamparkinglot.parkinggo.member.entity.Member;

@Component
public class MemberMapper {

    public Member memberJoinDtoToMember(MemberJoinDto memberJoinDto) {
        return Member.builder()
                .email(memberJoinDto.getEmail())
                .password(memberJoinDto.getPassword())
                .build();
    }

}
