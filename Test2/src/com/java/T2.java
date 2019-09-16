package com.java;

public class T2 {
	
	public void test(){
		T1 t1 = new T1();
		
		t1.push();
		t1.push();
		t1.pop();
		t1.push();
		t1.push();
		
		for(int i = 0; i < t1.stack.length; i++) {
			System.out.println(t1.stack[(t1.stack.length-1)-i]);		
		}
	}
}
