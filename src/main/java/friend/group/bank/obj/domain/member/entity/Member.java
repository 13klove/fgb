package friend.group.bank.obj.domain.member.entity;

import com.google.common.collect.Lists;
import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import friend.group.bank.obj.domain.member.status.MemberAuthority;
import friend.group.bank.obj.domain.member.status.PaydayStauts;
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

    private Integer payday;

    @Enumerated(EnumType.STRING)
    private PaydayStauts paydayStauts;

    @Enumerated(EnumType.STRING)
    private MemberAuthority memberAuthority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MailLog> mailLogs = Lists.newArrayList();

    public void addMailLog(MailLog mailLog){
        mailLogs.add(mailLog);
    }

    protected Member(String name, String phone, Integer payday, PaydayStauts paydayStauts, MemberAuthority memberAuthority) {
        this.name = name;
        this.phone = phone;
        this.payday = payday;
        this.memberAuthority = memberAuthority;
    }

    public static Member createMember(String name, String phone, Integer payday, PaydayStauts paydayStauts, MemberAuthority memberAuthority){
        return new Member(name, phone, payday, paydayStauts, memberAuthority);
    }

}
