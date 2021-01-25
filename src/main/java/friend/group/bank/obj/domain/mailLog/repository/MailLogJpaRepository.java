package friend.group.bank.obj.domain.mailLog.repository;

import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailLogJpaRepository extends JpaRepository<MailLog, Long> {
}
