package friend.group.bank.obj.job.sendPaydaySmsJob.writer;

import friend.group.bank.obj.domain.mailLog.entity.MailLog;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SendPaydaySmsWriter extends JpaItemWriter<MailLog> {

    private final EntityManagerFactory entityManagerFactory;
    private final CloseableHttpClient httpClient;

    public SendPaydaySmsWriter(EntityManagerFactory entityManagerFactory, CloseableHttpClient httpClient) {
        this.entityManagerFactory = entityManagerFactory;
        this.httpClient = httpClient;
        super.setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void write(List<? extends MailLog> items) {
        System.out.println(items);
        super.write(items);
    }
}
