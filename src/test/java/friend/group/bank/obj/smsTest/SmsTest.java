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
    String key = "*";

    @Test
    public void oneCall()throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        SmsDto smsDto = SmsDto.userCall("test header", "test context", "*");
        smsDto.setKey(key);
        smsDto.setSender("*");
        smsDto.setUsername("*");
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

        SmsDto smsDto = SmsDto.userCall("test header", "test context", Arrays.asList("*", "*"));
        smsDto.setKey(key);
        smsDto.setSender("*");
        smsDto.setUsername("*");
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
        smsApi.sendSms(SmsDto.userCall("apiTest", "apiTestContext", "*"));
    }

    @Test
    public void getMoney(){
        SmsApi smsApi = new SmsApi(httpClient);
        Double chargeCheck = smsApi.getChargeCheck();
        System.out.println(chargeCheck);
    }

}
