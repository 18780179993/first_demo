package com.example.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MybatisAutoConfiguration.class)
@MapperScan(basePackages= {"com.example.mapper"})
public class MybatiseConfig {

}
