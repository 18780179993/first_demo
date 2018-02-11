package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.config.dynamic.datasource.DynamicDataSourceRegister;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages= {"com.example"})
@Import({DynamicDataSourceRegister.class})
public class MyCode1Application implements CommandLineRunner{

	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(MyCode1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务启动成功");
	}
	
}
