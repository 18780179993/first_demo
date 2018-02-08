package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.dynamic.datasource.config.DynamicDataSourceRegister;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages= {"com.example"})
@Import({DynamicDataSourceRegister.class})
public class MyCode1Application {

	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(MyCode1Application.class, args);
	
		ctx.getBean(Test.class).test();
	}
}
