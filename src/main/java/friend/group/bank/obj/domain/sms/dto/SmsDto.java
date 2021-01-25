package friend.group.bank.obj.domain.sms.dto;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class SmsDto {

    private String title;

    private String message;

    private String sender;

    private List<SmsMobileDto> receiver;

    private String key;

    private String username;

    private String type;

    public void smsOption(String username, String key, String sender){
        this.key = key;
        this.username = username;
        this.sender = sender;
        this.type = "java";
    }

    public static SmsDto userCall(String title, String message, String receiver){
        return SmsDto.builder().title(title).message(message).receiver(Arrays.asList(new SmsMobileDto(receiver))).build();
    }

    public static SmsDto userCall(String title, String message, List<String> receivers){
        return SmsDto.builder().title(title).message(message).receiver(receivers.stream().map(SmsMobileDto::new).collect(Collectors.toList())).build();
    }

    public static SmsDto adminCall(String title, String message, String receiver){
        return SmsDto.builder().title(title).message(message).receiver(Arrays.asList(new SmsMobileDto(receiver))).build();
    }

}
