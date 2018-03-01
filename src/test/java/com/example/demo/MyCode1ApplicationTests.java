package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCode1ApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		
	 new Thread("t1"){
		 public void run() {
			 int i=0;
			 while (true) {
				i++;
				try {
					Thread.sleep(500l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		 };
	 }.start();
	 new Thread("t2"){
		 public void run() {
			 int j=0;
			 while (true) {
				j++;
				try {
					Thread.sleep(500l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		 };
	 }.start();
	}
}
