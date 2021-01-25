package friend.group.bank.obj.job.sendPaydaySmsJob.writer;

import friend.group.bank.obj.domain.mailLog.entity.MailLog;
import friend.group.bank.obj.domain.mailLog.status.SmsStatus;
import friend.group.bank.obj.domain.member.entity.Member;
import friend.group.bank.obj.domain.member.repository.MemberJpaRepository;
import friend.group.bank.obj.domain.member.status.MemberAuthority;
import friend.group.bank.obj.domain.sms.dto.SmsDto;
import friend.group.bank.obj.domain.sms.parser.SmsApi;
import friend.group.bank.obj.util.aes.AES256Util;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SendPaydaySmsWriter extends JpaItemWriter<MailLog> {

    private final MemberJpaRepository memberJpaRepository;
    private final CloseableHttpClient httpClient;
    private final AES256Util aes256Util;
    private static final String adminHeader = "금액부족";
    private static final String adminText = "금액부족 fgb";

    public SendPaydaySmsWriter(EntityManagerFactory entityManagerFactory, CloseableHttpClient httpClient, AES256Util aes256Util, MemberJpaRepository memberJpaRepository) {
        this.httpClient = httpClient;
        this.aes256Util = aes256Util;
        this.memberJpaRepository = memberJpaRepository;
        super.setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void write(List<? extends MailLog> items) {
        SmsApi smsApi = new SmsApi(httpClient);

        items.forEach(a -> {
            if(smsApi.sendSms(SmsDto.userCall(a.getHeader(), a.getText(), aes256Util.aesDecode(a.getMember().getPhone())))){
                a.sendResult(SmsStatus.SUCCESS);
            }else{
                a.sendResult(SmsStatus.FAIL);
            }
        });

        cashCheck(smsApi);
        super.write(items);
    }

    public void cashCheck(SmsApi smsApi){
        if(smsApi.getChargeCheck() < 1000){
            Member byMemberAuthority = memberJpaRepository.findByMemberAuthority(MemberAuthority.ADMIN);
            smsApi.sendSms(SmsDto.adminCall(adminHeader, adminText, aes256Util.aesDecode(byMemberAuthority.getPhone())));
        }
    }
}
