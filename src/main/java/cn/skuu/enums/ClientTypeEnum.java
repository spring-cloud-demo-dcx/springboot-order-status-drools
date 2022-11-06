package cn.skuu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ClientTypeEnum {
    ANDROID(1),
    IOS(2),
    PC(3),
    ;
    private final int code;

    public static ClientTypeEnum getByCode(int clientCode) {
        return Arrays.stream(values()).filter(a -> a.code == clientCode).findFirst().orElse(null);
    }
}
