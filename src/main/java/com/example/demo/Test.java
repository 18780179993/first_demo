package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
	
	@Autowired
	ServiceTest serviceTest;
	public void test() {
		serviceTest.db1();
		
		serviceTest.db2();
	}


}
