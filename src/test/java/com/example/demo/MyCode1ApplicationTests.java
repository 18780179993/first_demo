package com.example.demo;

//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.locks.Lock;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.A;
import com.example.entity.WaiterSaleCount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyCode1Application.class)
public class MyCode1ApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
//
//	public static void main(String[] args) {
//		 final Object lock=new Object();
//		
//	 new Thread("t1"){
//		 public void run() {
//			 synchronized (lock) {
//				 System.out.println("t1");
//				 try {
//					lock.wait();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("t1_end");
//			}
//			
//		 };
//	 }.start();
//	 new Thread("t2"){
//		 public void run() {
//			 synchronized (lock) {
//				 System.out.println("t2");
//				 try {
//					lock.wait();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("t2_end");
//			}
//			
//		 };
//	 }.start();
//	}

//	@Resource(name="mongoTemplate1")
//	private MongoTemplate m;
	

	@Test
	public void testRedis() throws Exception {
//		Query q=new Query();
//		q.limit(1);
//		List<WaiterSaleCount> l=m.find(q, WaiterSaleCount.class);
//		System.out.println(l);
	}
}
