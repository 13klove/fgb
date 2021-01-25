package friend.group.bank.obj.util.aes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class AESConfig {

    private static final String seKey = "abfrdaswer1236546qewrqw231asdf5";

    @Bean
    public AES256Util aes256Util(){
        try {
            return new AES256Util(seKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
