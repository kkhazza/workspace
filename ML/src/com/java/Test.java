package com.java;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map <String, String> inmap = new HashMap<String, String>();
		List<Map <String, String>> inlist = new ArrayList<Map <String, String>>();
		Map <String, List> outmap = new HashMap<String, List>();
		
		inmap.put("김민정", "내짝궁");
		inmap.put("조은별", "내앞자리");
		inlist.add(inmap);
		outmap.put("숙제", inlist);
		
		System.out.println(outmap);
	}

}
