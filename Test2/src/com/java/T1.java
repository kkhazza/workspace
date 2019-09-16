package com.java;
public class T1 {
    int top = -1;
    int data = 0;
	String[] stack = new String[8];
	
	public T1() {
		for(int i = 0; i < stack.length; i++) {
			stack[i] = null;
		}
		
	}	
	public void push() {
		if(top == 7) {
			System.out.println("Overflow :(");
		} else {
			stack[++top] = Integer.toString(++data);
		}
		if(data > 8) {
			stack[top] = null;
		}		
	}
	
	public void pop() {
		if(top == -1) {
			System.out.println("Underflow :(");
		} else {
			stack[top] = null;
			top--;
		}		
	}
	
}
