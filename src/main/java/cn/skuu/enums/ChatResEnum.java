package cn.skuu.enums;

import cn.skuu.common.enums.ICode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatResEnum implements ICode {

    CLIENT_TYPE_ERROR(100000, "客户端类型不正确"),
    PHONE_NOT_EXIST(100001, "号码不存在"),
    PHONE_HAS_SET(100002, "号码已经设置"),
    PHONE_HAS_USE(100003, "号码已经被使用"),
    ADD_PHONE_ERROR(100004, "添加号码失败"),
    ERROR1(100005, "updateStatusWithRoom"),
    USER_NOT_IN_ROOM(100006, "用户不在邀请人中"),
    ROOM_NOT_EXIST(100007, "房间不存在"),
    TO_MANY_USER(100008, "邀请人数超出限制"),
    ;

    private final int code;
    private final String msg;
}
