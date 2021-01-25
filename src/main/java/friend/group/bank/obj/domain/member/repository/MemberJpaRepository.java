package friend.group.bank.obj.domain.member.repository;

import friend.group.bank.obj.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
