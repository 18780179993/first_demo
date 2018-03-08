package com.example.demo;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.A;
import com.example.entity.WaiterSaleCount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyCode1Application.class)
public class MyCode1ApplicationTests {

	@Resource(name="mongoTemplate1")
	private MongoTemplate m;
	

	@Test
	public void testRedis() throws Exception {
		Query q=new Query();
		q.limit(1);
		List<WaiterSaleCount> l=m.find(q, WaiterSaleCount.class);
		System.out.println(l);
	}
}
