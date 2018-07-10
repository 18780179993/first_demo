package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.entity.A;

public class FastJsonTest {

	public static void main(String[] args) {
		List<A> la=new ArrayList<>();
		A a=new A();
		a.setNumb(123);
		la.add(a);
		String jo=JSON.toJSONString(la);
		System.out.println(jo);
		Class<A> c=A.class;
		System.out.println(c);
		List<A> lb=JSON.parseArray(jo, c);
		System.out.println(lb);
		
	}
	
}
