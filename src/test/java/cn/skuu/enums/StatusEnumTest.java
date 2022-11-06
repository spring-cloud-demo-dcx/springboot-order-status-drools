package cn.skuu.enums;

import org.junit.jupiter.api.Test;

class StatusEnumTest {

    @Test
    void getCode() {
        StringBuilder sb = new StringBuilder();
        for (StatusEnum value : StatusEnum.values()) {
            sb.append(value.getCode()).append(":").append(value.getMsg());
        }
        System.out.println(sb);
    }
}