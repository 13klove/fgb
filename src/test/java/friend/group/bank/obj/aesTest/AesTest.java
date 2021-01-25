package friend.group.bank.obj.aesTest;

import friend.group.bank.obj.util.aes.AES256Util;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

public class AesTest {

    @Test
    public void aesTest(){
        try {
            AES256Util aes256Util = new AES256Util("abfrdaswer1236546qewrqw231asdf5");
            String s = aes256Util.aesEncode("01099153163");
            System.out.println(s);
            String s1 = aes256Util.aesDecode(s);
            System.out.println(s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
