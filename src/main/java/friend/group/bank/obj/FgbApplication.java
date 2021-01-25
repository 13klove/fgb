package friend.group.bank.obj;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FgbApplication {

    public static void main(String[] args) {
        SpringApplication.run(FgbApplication.class, args);
    }

}
