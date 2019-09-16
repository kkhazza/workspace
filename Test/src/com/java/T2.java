package com.java;

public class T2 {
	
	public void test(){
        // 크기 8의 배열 스택 생성
        T1 arrayStack = new T1(8);
        
        System.out.println("Array Stack 테스트");
        
        // 스택에 데이터 삽입
        for(int i=1; i<=4; i++){
            arrayStack.push("ArrayStack 데이터 : " + i);
            if(i==2) {
            	arrayStack.pop();
            }
        }
        
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        
	}
}
