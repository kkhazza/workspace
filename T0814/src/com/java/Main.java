package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static int NO = 1;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		T1 t1 = new T1();
		T1 t1 = (T1) context.getBean("t1");
		t1.create();
		t1.print();
		t1.update();
		t1.print(); 
		t1.delete();
		t1.print();
	}

}
