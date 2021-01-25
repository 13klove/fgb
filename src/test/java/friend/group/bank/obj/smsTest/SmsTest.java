package friend.group.bank.obj.smsTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import friend.group.bank.obj.util.crw.HttpClientFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SmsTest {

    String url = "https://directsend.co.kr/index.php/api_v2/sms_change_word";
    String key = "t40sQYNw7nPHVS2";

    @Test
    public void test()throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        CloseableHttpClient httpClient = HttpClientFactory.httpClient(50, 3000);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");

        Gson gson = new Gson();
        List<Map<String, String>> maps = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        map.put("title", "테스트 입니다.");
        map.put("message", "test");
        map.put("sender", "01099153163");
        map.put("username", "13klove");
        map.put("key", key);
        map.put("type", "java");
        maps.add(map);

        Map<String, String> map1 = Maps.newHashMap();
        map1.put("title", "테스트 입니다.");
        map1.put("message", "test");
        map1.put("sender", "01099144669");
        map1.put("username", "13klove");
        map1.put("key", key);
        map1.put("type", "java");
        maps.add(map1);

        Map<String, String> map2 = Maps.newHashMap();
        map2.put("title", "테스트 입니다.");
        map2.put("message", "test");
        map2.put("sender", "01042126478");
        map2.put("username", "13klove");
        map2.put("key", key);
        map2.put("type", "java");
        maps.add(map2);

        Map<String, String> map3 = Maps.newHashMap();
        map3.put("title", "테스트 입니다.");
        map3.put("message", "test");
        map3.put("sender", "01050393074");
        map3.put("username", "13klove");
        map3.put("key", key);
        map3.put("type", "java");
        maps.add(map3);

        StringEntity requestEntity = new StringEntity(
                gson.toJson(maps),
                ContentType.APPLICATION_JSON);

        httpPost.setEntity(requestEntity);
        System.out.println(gson.toJson(maps));
        CloseableHttpResponse execute = httpClient.execute(httpPost);
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
