package friend.group.bank.obj.job.sendPaydaySmsJobTest;

import friend.group.bank.obj.BatchTestConfig;
import friend.group.bank.obj.job.sendPaydaySmsJob.SendPaydaySmsConfig;
import friend.group.bank.obj.util.crw.CrwConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest(classes = {SendPaydaySmsConfig.class, BatchTestConfig.class, CrwConfig.class})
public class SendPaydaySmsJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void sendPaydaySmsJobTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20200824")
                .addDate("basicDate", new Date())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        log.info("batchJob: {}", jobExecution);
    }

}
