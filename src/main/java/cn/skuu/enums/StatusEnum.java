package cn.skuu.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    DEF(0, "默认"),
    ONLINE(6, "在线"),
    WAIT(1, "被邀请等待"),
    INVITE(2, "主动邀请中"),
    CALLING(3, "通话中"),
    OFFLINE(4, "离线"),
    KICK_OUT(5, "踢出"),
    ;
    private final int code;
    private final String msg;

    public static List<Integer> getRoomUserIds() {
        return Lists.newArrayList(
                DEF.getCode(),
                ONLINE.getCode(),
                WAIT.getCode(),
                INVITE.getCode(),
                CALLING.getCode(),
                OFFLINE.getCode()
        );
    }

    public static List<Integer> getCallStatus() {
        return Lists.newArrayList(
                ONLINE.getCode(),
                WAIT.getCode(),
                INVITE.getCode(),
                CALLING.getCode()
        );
    }
}
