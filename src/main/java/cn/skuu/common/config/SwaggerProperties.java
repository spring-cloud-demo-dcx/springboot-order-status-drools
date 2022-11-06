package cn.skuu.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dcx
 * @since 2022-08-13 03:36
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.swagger")
public class SwaggerProperties {
    private Boolean enable;
    private String groupName;
    private String basePackage;
    private String version;
    private String title;
    private String description;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
    private String host;
}
