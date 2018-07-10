package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.A;
import com.example.service.InsertService;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	InsertService serviceTest;
	
	@RequestMapping("/insert")
	public void test() {
		A a=new A();
		a.setNumb(3);
		serviceTest.use2db(a);
	}

}
