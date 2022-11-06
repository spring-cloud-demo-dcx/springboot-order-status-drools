package cn.skuu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CallStatusEnum {
    NOT_IN_CALL(0),
    CALLING(1);
    private int code;
}
