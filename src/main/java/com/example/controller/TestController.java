package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ServiceTest;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	ServiceTest serviceTest;
	
	@RequestMapping("/insert")
	public void test() {
		serviceTest.test();
	}

}
