package friend.group.bank.obj.member;

import com.google.common.collect.Lists;
import friend.group.bank.obj.domain.member.entity.Member;
import friend.group.bank.obj.domain.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(value = false)
    public void insertMember(){
        List<Member> members = Lists.newArrayList();
        members.add(Member.createMember("hb", "010", "10"));
        members.add(Member.createMember("jh", "0101", "25"));
        members.add(Member.createMember("sy", "0102", "15"));
        members.add(Member.createMember("ys", "0103", "25"));
        memberJpaRepository.saveAll(members);
        entityManager.flush();
    }

}
