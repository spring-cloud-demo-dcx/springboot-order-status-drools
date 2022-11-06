package cn.skuu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyEnum {
    REJECT(0, "拒绝"),
    ACCEPT(1, "同意"),
    VIDEOING(2, "通话中"),
    ;
    private int code;
    private String msg;
}
