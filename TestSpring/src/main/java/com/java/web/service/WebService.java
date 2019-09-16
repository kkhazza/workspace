package com.java.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service //인젝션 메모리주소 싱글톤 
public class WebService {
	private static HashMap<String, Object> User = new HashMap<String, Object>();
	private static List<HashMap<String, Object>> comment = new ArrayList<HashMap<String, Object>>();
	static {
		User.put("test", "1234");
		User.put("khj", "1234");
	}
	
	public HashMap<String, Object> getUser(){
		return User;
	}
	
	public List<HashMap<String, Object>> getComment(){
		return comment;
	}
}
