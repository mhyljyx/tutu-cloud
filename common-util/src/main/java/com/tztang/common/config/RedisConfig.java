package com.tztang.common.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.database.auth_db}")
    private int auth_db;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int max_active;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Duration max_wait;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int max_idle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int min_idle;

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(max_idle);
        poolConfig.setMaxTotal(max_active);
        poolConfig.setMinIdle(min_idle);
        poolConfig.setMaxWait(max_wait);
        return poolConfig;
    }

    @Bean(name = "stringRedisTemplateAuth_db")
    public StringRedisTemplate getStringRedisTemplateAuth_db() {
        return getStringRedisTemplate(auth_db);
    }

    private StringRedisTemplate getStringRedisTemplate(int database) {
        // 构建工厂对象
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(timeout))
                .poolConfig(genericObjectPoolConfig())
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        // 设置使用的redis数据库
        factory.setDatabase(database);
        // 重新初始化工厂
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }
}
