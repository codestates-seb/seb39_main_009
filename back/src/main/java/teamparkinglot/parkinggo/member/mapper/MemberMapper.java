package teamparkinglot.parkinggo.member.mapper;

import org.springframework.stereotype.Component;
import teamparkinglot.parkinggo.member.dto.MemberJoinDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.member.entity.MemberRole;

@Component
public class MemberMapper {

    public Member memberJoinDtoToMember(MemberJoinDto memberJoinDto) {
        return Member.builder()
                .email(memberJoinDto.getEmail())
                .password(memberJoinDto.getPassword())
                .phone(memberJoinDto.getPhoneNum())
                .name(memberJoinDto.getName())
                .carNumber(memberJoinDto.getCarNumber())
                .svcUseAgmt(memberJoinDto.isSvcUseAgmt())
                .psInfoAgmt(memberJoinDto.isPsInfoAgmt())
                .eventAgmt(memberJoinDto.isEventAgmt())
                .role(MemberRole.USER)
                .build();
    }

}
