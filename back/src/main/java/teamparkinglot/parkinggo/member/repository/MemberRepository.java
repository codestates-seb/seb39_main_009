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

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQueryDsl {
    Optional<Member> findByEmail(String email);
}
