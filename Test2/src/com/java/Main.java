package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		T2 t2 = (T2) context.getBean("t2");
		t2.test();
		
	}

}
