package friend.group.bank.obj.job.sendPaydaySmsJob.processor;

import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import friend.group.bank.obj.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class SendPaydaySmsProcessor implements ItemProcessor<Member, MailLog> {

    private static final String header = "정통 회비 입금안내";
    private static final String text = "정통 회비 입금하세요. 급여날이 다가왔습니다.";

    @Override
    public MailLog process(Member member) {
        MailLog mailLog = MailLog.createMailLog(header, text);
        mailLog.changeMember(member);
        return mailLog;
    }

}
