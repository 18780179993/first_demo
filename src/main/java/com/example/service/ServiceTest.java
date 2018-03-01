package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entity.A;

@Service
public class ServiceTest {
	
	@Autowired
	InsertService serviceTest;
	
	
	public void test() {
		
		A a=new A();
		a.setNumb(1);
		serviceTest.use2db(a);
	}


}
