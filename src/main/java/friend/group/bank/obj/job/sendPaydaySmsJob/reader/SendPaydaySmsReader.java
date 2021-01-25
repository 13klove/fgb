package friend.group.bank.obj.job.sendPaydaySmsJob.reader;

import friend.group.bank.obj.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
public class SendPaydaySmsReader {

    private final EntityManagerFactory entityManagerFactory;
    private static final int sendPaydaySmsPageSize = 10;

    public JpaPagingItemReader sendPaydaySmsReader(){
        return new JpaPagingItemReaderBuilder<Member>()
                .queryString("select m from Member m")
                .pageSize(sendPaydaySmsPageSize)
                .entityManagerFactory(entityManagerFactory)
                .name("sendPaydaySmsReader")
                .build();
    }

}
