package friend.group.bank.obj.domain.mailLog.status;

public enum SmsStatus {
    SUCCESS("성공"),
    FAIL("실패");

    private String desc;

    SmsStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
