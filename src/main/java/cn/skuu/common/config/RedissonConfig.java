package cn.skuu.common.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Redisson配置
 *
 * @author dcx
 * @date 2022/11/6 18:10
 **/
@Configuration
@ConditionalOnProperty(prefix = "spring.redis", name = "enabled", havingValue = "true")
public class RedissonConfig {

    @Resource
    private RedisProperties redisProperties;
    private static final String PREFIX = "redis://";

    @Bean
    public Redisson getRedisson() {
        Config config = new Config();
        String url = PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer()
                .setAddress(url)
                .setDatabase(redisProperties.getDatabase())
                .setPassword(redisProperties.getPassword())
                // 设置1秒钟ping一次来维持连接
                .setPingConnectionInterval(1000);
        return (Redisson) Redisson.create(config);
    }

}
