package friend.group.bank.obj.job.sendPaydaySmsJob.processor;

import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import friend.group.bank.obj.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class SendPaydaySmsProcessor implements ItemProcessor<Member, MailLog> {

    private static final String header = "정통 회비 입금안내";
    private static final String text = "당신 급여날에 가깝습니다. 얼른 입금하세요.!";

    @Override
    public MailLog process(Member member) {
        System.out.println(member);
        MailLog mailLog = MailLog.createMailLog(header, text);
        mailLog.changeMember(member);
        return mailLog;
    }

}
