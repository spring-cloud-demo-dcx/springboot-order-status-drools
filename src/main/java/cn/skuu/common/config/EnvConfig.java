package cn.skuu.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dcx
 * @since 2022-11-06 17:46
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.profiles")
public class EnvConfig {
    private String env;
}
