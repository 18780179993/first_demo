package com.example.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class DefaultRedisConfig extends RedisConfig {
	
	@Value("${spring.redis.database}")
    private int dbIndex;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    
    @Primary
    @Bean
    public JedisConnectionFactory defaultRedisConnectionFactory() {
        return newJedisConnectionFactory(dbIndex,host, port, timeout);
    }
    
    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate<String,Object> defaultRedisTemplate() {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

}
