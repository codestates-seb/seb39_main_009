package teamparkinglot.parkinggo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
