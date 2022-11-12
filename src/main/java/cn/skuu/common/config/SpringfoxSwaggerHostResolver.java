//package cn.skuu.common.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.servers.Server;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import springfox.documentation.oas.web.OpenApiTransformationContext;
//import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
//import springfox.documentation.spi.DocumentationType;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collections;
//
///**
// * @author dcx
// * @since 2022-09-18 19:43
// **/
//public class SpringfoxSwaggerHostResolver {
//
//    @Autowired
//    private SwaggerProperties swaggerProperties;
//
//    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
//        OpenAPI swagger = context.getSpecification();
//        Server server = new Server();
//        server.setUrl(swaggerProperties.getHost());
//        swagger.setServers(Collections.singletonList(server));
//        return swagger;
//    }
//
//    public boolean supports(@NotNull DocumentationType delimiter) {
//        return DocumentationType.OAS_30.equals(delimiter);
//    }
//}
