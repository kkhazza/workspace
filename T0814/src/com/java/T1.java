package com.java;

import java.util.Scanner;

public class T1 {
	int index = 0;
	int max = 5;
	String[] t = new String[max];	
	Scanner scan = new Scanner(System.in);
	public void create() {
		while (true) {
			String input = scan.next().toUpperCase();
			if("EXIT".equals(input)) break;
			if(index == max) break;
			t[index++] = input;
		}
	}
	public void print() {
		for (String value : t) {
			if(value == null) continue;
			System.out.println(value);
		}
	}
	
	public void update() {
		System.out.println("update");
//		while(true) {
//			
//		}
		int n = scan.nextInt();
		String e = scan.next();
		t[n] = e;
	}
	
	public void delete() {
		System.out.println("delete");
		int n = scan.nextInt();
		t[n] = null;
	}
}
