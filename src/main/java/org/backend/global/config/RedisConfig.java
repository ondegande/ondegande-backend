package org.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.data.redis.port}")
    private int REDIS_PORT;

    // 로컬에서만 해당
//    @Value("${spring.data.redis.password}")
//    private String REDIS_PASSWORD;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(REDIS_HOST);
        configuration.setPort(REDIS_PORT);
//        configuration.setPassword(REDIS_PASSWORD);

        return new LettuceConnectionFactory(configuration);
    }

}
