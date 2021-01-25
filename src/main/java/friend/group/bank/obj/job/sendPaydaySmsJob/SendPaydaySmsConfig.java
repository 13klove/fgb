package friend.group.bank.obj.job.sendPaydaySmsJob;

import friend.group.bank.obj.domain.member.entity.Member;
import friend.group.bank.obj.job.sendPaydaySmsJob.processor.SendPaydaySmsProcessor;
import friend.group.bank.obj.job.sendPaydaySmsJob.reader.SendPaydaySmsReader;
import friend.group.bank.obj.job.sendPaydaySmsJob.writer.SendPaydaySmsWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@Transactional
@RequiredArgsConstructor
public class SendPaydaySmsConfig {

    private final CloseableHttpClient httpClient;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int sendPaydaySmsChunk = 10;

    @Bean
    public Job sendPaydaySmsJob(){
        return jobBuilderFactory.get("sendPaydaySmsJob")
                .start(sendPaydaySmsStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step sendPaydaySmsStep(){
        return stepBuilderFactory.get("sendPaydaySmsStep")
                .<Member, Member>chunk(sendPaydaySmsChunk)
                .reader(new SendPaydaySmsReader(entityManagerFactory).sendPaydaySmsReader())
                .processor(new SendPaydaySmsProcessor())
                .writer(new SendPaydaySmsWriter(entityManagerFactory, httpClient))
                .build();
    }

}
