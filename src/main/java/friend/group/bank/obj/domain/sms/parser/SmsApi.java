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
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SmsApi {

    private final CloseableHttpClient httpClient;

    private static final String sendSmsUrl = "https://directsend.co.kr/index.php/api_v2/sms_change_word";
    private static final String chargeUrl = "https://directsend.co.kr/index.php/api_v2/remaining_money";
    private static final String key = "*";
    private static final String userName = "*";
    private static final String sender = "*";

    public Boolean sendSms(SmsDto smsDto) {
        smsDto.smsOption(userName, key, sender);

        HttpPost httpPost = new HttpPost(sendSmsUrl);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(smsDto), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        try(CloseableHttpResponse execute = httpClient.execute(httpPost)) {
            String responseText = EntityUtils.toString(execute.getEntity());
            Map<String, String> result = gson.fromJson(responseText, HashMap.class);
            return result.get("status").equals("0");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Double getChargeCheck() {
        HttpPost httpPost = new HttpPost(chargeUrl);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(SmsDto.builder().username(userName).key(key).build()), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        try(CloseableHttpResponse execute = httpClient.execute(httpPost)) {
            String responseText = EntityUtils.toString(execute.getEntity());
            Map<String, String> result = gson.fromJson(responseText, HashMap.class);
            return Double.valueOf(result.get("point"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
