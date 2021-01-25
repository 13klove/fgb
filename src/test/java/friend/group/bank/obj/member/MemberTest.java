package friend.group.bank.obj.member;

import com.google.common.collect.Lists;
import friend.group.bank.obj.domain.member.entity.Member;
import friend.group.bank.obj.domain.member.repository.MemberJpaRepository;
import friend.group.bank.obj.domain.member.status.MemberAuthority;
import friend.group.bank.obj.util.aes.AES256Util;
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
    @Autowired
    private AES256Util aes256Util;

    @Test
    @Transactional
    @Rollback(value = false)
    public void insertMember() {
        List<Member> members = Lists.newArrayList();
        members.add(Member.createMember("hb", aes256Util.aesEncode("123"), "10", MemberAuthority.ADMIN));
        members.add(Member.createMember("jh", aes256Util.aesEncode("7586"), "25", MemberAuthority.USER));
        members.add(Member.createMember("sy", aes256Util.aesEncode("789"), "25", MemberAuthority.USER));
        members.add(Member.createMember("ys", aes256Util.aesEncode("456"), "25", MemberAuthority.USER));
        memberJpaRepository.saveAll(members);
        entityManager.flush();
    }

    @Test
    public void readMember(){
        memberJpaRepository.findAll().forEach(a-> System.out.println(aes256Util.aesDecode(a.getPhone())));
    }

    @Test
    public void adminMember(){
        Member byMemberAuthority = memberJpaRepository.findByMemberAuthority(MemberAuthority.ADMIN);
        System.out.println(byMemberAuthority);
        System.out.println(aes256Util.aesDecode(byMemberAuthority.getPhone()));
    }

}
