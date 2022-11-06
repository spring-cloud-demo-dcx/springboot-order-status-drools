package cn.skuu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnvEnum {
    DEV("dev"),
    PRD("prd");
    private final String env;
}
