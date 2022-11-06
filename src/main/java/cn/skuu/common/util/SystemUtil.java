package cn.skuu.common.util;

import cn.skuu.common.enums.EnvEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统公交
 *
 * @author dcx
 * @since 2022-11-06 17:53
 **/
@Component
public class SystemUtil {
    private static String env;

    @Autowired
    public static void setEnv(String env) {
        SystemUtil.env = env;
    }

    public boolean isDev() {
        return env.equalsIgnoreCase(EnvEnum.DEV.getEnv());
    }

    public boolean isPrd() {
        return env.equalsIgnoreCase(EnvEnum.PRD.getEnv());
    }
}
