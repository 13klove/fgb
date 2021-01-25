package friend.group.bank.obj.domain.member.entity;

import com.google.common.collect.Lists;
import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@ToString(exclude = {"mailLogs"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    private String name;

    private String phone;

    private String payday;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MailLog> mailLogs = Lists.newArrayList();

    public void addMailLog(MailLog mailLog){
        mailLogs.add(mailLog);
    }

    protected Member(String name, String phone, String payday) {
        this.name = name;
        this.phone = phone;
        this.payday = payday;
    }

    public static Member createMember(String name, String phone, String payday){
        return new Member(name, phone, payday);
    }

}
