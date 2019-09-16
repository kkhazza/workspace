package com.mytest.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service //service로 어노테이션!
public class HomeService {

	public Map<String, Object> getData(){
		return new HashMap<String, Object>();
	}
}
