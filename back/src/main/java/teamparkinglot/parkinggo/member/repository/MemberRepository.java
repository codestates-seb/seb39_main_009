package teamparkinglot.parkinggo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.member.dto.MyPageResDto;
import teamparkinglot.parkinggo.member.dto.SidebarDto;
import teamparkinglot.parkinggo.member.entity.Member;
import teamparkinglot.parkinggo.reservation.dto.ReservationResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select new teamparkinglot.parkinggo.member.dto.MyPageResDto(m.id, m.email, m.phone, m.carNumber) from Member m where m.email = :email")
    MyPageResDto findMyPage(@Param("email") String email);

    @Query("select new teamparkinglot.parkinggo.member.dto.SidebarDto(m.nickname, m.id, m.email, m.reservation.size ,m.point, m.phone, m.carNumber) from Member m where m.email = :email")
    SidebarDto findSidebar(@Param("email") String email);

}
