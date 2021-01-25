package friend.group.bank.obj.smsTest;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import friend.group.bank.obj.domain.sms.dto.SmsDto;
import friend.group.bank.obj.domain.sms.parser.SmsApi;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest
public class SmsTest {

    @Autowired
    CloseableHttpClient httpClient;
    String url = "https://directsend.co.kr/index.php/api_v2/sms_change_word";
    String key = "t40sQYNw7nPHVS2";

    @Test
    public void oneCall()throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        SmsDto smsDto = SmsDto.userCall("test header", "test context", "01099153163");
        smsDto.setKey(key);
        smsDto.setSender("000000");
        smsDto.setUserName("13klove");
        smsDto.setType("java");

        Gson gson = new Gson();

        StringEntity requestEntity = new StringEntity(gson.toJson(smsDto), ContentType.APPLICATION_JSON);

        httpPost.setEntity(requestEntity);
        System.out.println(gson.toJson(smsDto));
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        String responseText = EntityUtils.toString(execute.getEntity());
        System.out.println(responseText);
    }

    @Test
    public void manyCall()throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        SmsDto smsDto = SmsDto.userCall("test header", "test context", Arrays.asList("01099153163", "01099153163"));
        smsDto.setKey(key);
        smsDto.setSender("000000");
        smsDto.setUserName("13klove");
        smsDto.setType("java");

        Gson gson = new Gson();

        StringEntity requestEntity = new StringEntity(gson.toJson(smsDto), ContentType.APPLICATION_JSON);

        httpPost.setEntity(requestEntity);
        System.out.println(gson.toJson(smsDto));
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        String responseText = EntityUtils.toString(execute.getEntity());
        System.out.println(responseText);
    }

    @Test
    public void smsApi(){
        SmsApi smsApi = new SmsApi(httpClient);
        smsApi.sendSms(SmsDto.userCall("apiTest", "apiTestContext", "01099153163"));
    }

    @Test
    public void jtest(){

        Map<String, String> map = Maps.newHashMap();
        map.put("title", "테스트 입니다.");
        map.put("message", "test");
        map.put("sender", "01099153163");
        map.put("username", "13klove");
        map.put("key", key);
        map.put("type", "java");
        Gson gson = new Gson();

        try {
            Connection.Response accept = Jsoup.connect(url)
                    .header("Cache-Control", "no-cache")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .header("Accept", "application/json")
                    .method(Connection.Method.POST)
                    .requestBody(gson.toJson(map))
                    .execute();

            System.out.println(accept.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
