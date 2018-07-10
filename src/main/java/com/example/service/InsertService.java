package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.dynamic.datasource.TargetDataSource;
import com.example.entity.A;
import com.example.mapper.AMapper;
import com.example.util.SpringBeanUtil;

@Service
public class InsertService {

	@Autowired
	AMapper mapper;
	
	@TargetDataSource("DB2")
	public void db1(A a) {
		mapper.insert(a);
	}
	
	@TargetDataSource("DEFAULT")
	public void db2(A a) {
		mapper.insert(a);
	}
	
	@TargetDataSource("DEFAULT")
	public void use2db(A a) {
		InsertService s=SpringBeanUtil.getBean(InsertService.class);
		s.db1(a);
		mapper.insert(a);
		
	}
	
}
