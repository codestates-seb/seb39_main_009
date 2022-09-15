package teamparkinglot.parkinggo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
