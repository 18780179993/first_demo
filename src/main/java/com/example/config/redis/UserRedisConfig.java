package com.example.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
@Configuration
public class UserRedisConfig extends RedisConfig {

    @Value("${spring.redis2.database}")
    private int dbIndex;
    @Value("${spring.redis2.host}")
    private String host;
    @Value("${spring.redis2.port}")
    private int port;
    @Value("${spring.redis2.timeout}")
    private int timeout;
    
    @Bean
    public JedisConnectionFactory userRedisConnectionFactory() {
        return newJedisConnectionFactory(dbIndex,host, port, timeout);
    }
    
    @Bean(name = "userRedisTemplate")
    public RedisTemplate<String,Object> userRedisTemplate() {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(userRedisConnectionFactory());
        setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.afterPropertiesSet();
        return template;
    }
}
