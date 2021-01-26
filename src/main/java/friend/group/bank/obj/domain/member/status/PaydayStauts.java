package friend.group.bank.obj.domain.member.status;

public enum PaydayStauts {
    FIX("급여일 고정"),
    FIRST("첫째주"),
    LAST("마지막주");

    private String desc;

    PaydayStauts(String desc) {
        this.desc = desc;
    }
}
