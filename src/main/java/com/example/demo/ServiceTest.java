package com.example.demo;

import org.springframework.stereotype.Service;

import com.example.dynamic.datasource.config.TargetDataSource;

@Service
public class ServiceTest {

	
	@TargetDataSource("DB2")
	public void db1() {
		
	}
	
	@TargetDataSource("DEFAULT")
	public void db2() {
		
	}
}
