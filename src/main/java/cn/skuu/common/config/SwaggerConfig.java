package cn.skuu.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author dcx
 * @since 2022-08-13 03:37
 **/
@Profile({"dev", "qa"})
@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableConfigurationProperties(value = {SwaggerProperties.class})
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;
    @Value("${spring.application.name}")
    private String name;

    @Bean
    public Docket adminApi() {
        // OAS_30：区别于 V2，（OpenAPI Specification 的简称 OAS）
        return new Docket(
                DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnable())
                // API 信息
                .apiInfo(getAdminApiInfo())
                .host(swaggerProperties.getHost())
                // API 分组
                .groupName(name)
                .select()
                // 对某个包的接口进行监听
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                // 监听所有接口
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 信息
     *
     * @return
     */
    private ApiInfo getAdminApiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title(swaggerProperties.getTitle())
                // 文档描述
                .description(swaggerProperties.getDescription())
                // 联系人信息
//                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                // 文档版本
                .version(swaggerProperties.getVersion())
                .build();
    }
}
