package friend.group.bank.obj.domain.sms.parser;

import com.google.gson.Gson;
import friend.group.bank.obj.domain.sms.dto.SmsDto;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class SmsApi {

    private final CloseableHttpClient httpClient;

    private static final String sendSmsUrl = "https://directsend.co.kr/index.php/api_v2/sms_change_word";
    private static final String chargeUrl = "https://directsend.co.kr/index.php/api_v2/remaining_money";
    private static final String key = "t40sQYNw7nPHVS2";
    private static final String userName = "13klove";
    private static final String sender = "00000000";

    public Boolean sendSms(SmsDto smsDto) {
        smsDto.smsOption(userName, key, sender);

        HttpPost httpPost = new HttpPost(sendSmsUrl);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(smsDto), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        System.out.println(gson.toJson(smsDto));

        try(CloseableHttpResponse execute = httpClient.execute(httpPost)) {
            String responseText = EntityUtils.toString(execute.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Integer getChargeCheck() {
        HttpPost httpPost = new HttpPost(chargeUrl);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(SmsDto.builder().userName(userName).key(key).build()), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        try(CloseableHttpResponse execute = httpClient.execute(httpPost)) {
            String responseText = EntityUtils.toString(execute.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
