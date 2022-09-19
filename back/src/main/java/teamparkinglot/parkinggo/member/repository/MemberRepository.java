package teamparkinglot.parkinggo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.member.dto.MyPageResDto;
import teamparkinglot.parkinggo.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m from Member m")
    List<Long> findRecentParkingByEmail(String email);

    @Query("select new teamparkinglot.parkinggo.member.dto.MyPageResDto(m.email, m.phone, m.carNumber) from Member m where m.email = :email")
    MyPageResDto findMyPage(@Param("email") String email);
}
