package friend.group.bank.obj.domain.member.status;

public enum MemberAuthority {

    USER("사용자"),
    ADMIN("관리자");

    private String desc;

    MemberAuthority(String desc) {
        this.desc = desc;
    }
}
