package cn.skuu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@MapperScan("cn.skuu.mapper")
public class SkuuApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkuuApplication.class, args);
    }

}
