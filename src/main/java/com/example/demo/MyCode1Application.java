package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.config.dynamic.datasource.DynamicDataSourceRegister;
import com.example.config.mongo.MultMongoTemplateConfig;
import com.mongodb.MongoConfigurationException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(basePackages= {"com.example.**"})
@Import({DynamicDataSourceRegister.class,MultMongoTemplateConfig.class})
public class MyCode1Application implements CommandLineRunner{

	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(MyCode1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("服务启动成功");
	}
	
}
