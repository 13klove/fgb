package friend.group.bank.obj.domain.mailLog.entity;

import friend.group.bank.obj.domain.mailLog.status.SmsStatus;
import friend.group.bank.obj.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member_log")
@ToString(exclude = {"member"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailLog {

    @Id
    @GeneratedValue
    private Long mailLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String header;

    private String text;

    @Enumerated(EnumType.STRING)
    private SmsStatus smsStatus;

    private LocalDateTime sendDateTime;

    protected MailLog(String header, String text, LocalDateTime sendDateTime){
        this.header = header;
        this.text = text;
        this.sendDateTime = sendDateTime;
    }

    public void sendResult(SmsStatus smsStatus){
        this.smsStatus = smsStatus;
    }

    public static MailLog createMailLog(String header, String text){
        return new MailLog(header, text, LocalDateTime.now());
    }

    public void changeMember(Member member){
        this.member = member;
        member.addMailLog(this);
    }

}
