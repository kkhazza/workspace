package com.mytest.web.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service  //인젝션 사용가능
public class WebService {
	/******************************************************
	 * 인젝션 >> 메모리주소를 줄이기 위해 사용 
	 * 자기자신에 계속 new를 해서 한주소만 사용하게 하는것
	 ******************************************************/
	
	private static HashMap<String, Object> User = new HashMap<String, Object>(); // 바뀌면 안되는 정보이기 때문에 해당 클래스에서만 쓸수있게 private 으로 선언해 주는게 좋음!!
	private static List<HashMap<String, Object>> comment = new ArrayList<HashMap<String, Object>>();
	static { // 정적으로 무언가를 실행하겠다는 의미
		User.put("test", "1234");
		User.put("yoo", "1234");
	}

	
	public HashMap<String, Object> getUser() {
		return User;
	}
	
	public List<HashMap<String, Object>> getcomment() {
		return comment;
	}
}
