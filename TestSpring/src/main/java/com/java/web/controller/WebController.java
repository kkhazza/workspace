package com.java.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.web.service.WebService;

import net.sf.json.JSONObject;

@Controller
public class WebController {
	
	@Autowired
	WebService ws;
	
	@RequestMapping("/exam")
	public String exam() {
		return "test";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public void test(HttpServletRequest req, HttpServletResponse res) {
		try {
			HashMap<String, Object> UserMap = ws.getUser();		
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			String Check = (String)UserMap.get(id); 
			
			try {
				if(Check.equals(pw)) {
					resultMap.put("nickName", id);
					resultMap.put("state", true);
				} 
			} catch(Exception e) {
				resultMap.put("state", false);
			}
			
			JSONObject jObj = JSONObject.fromObject(resultMap);
			res.getWriter().write(jObj.toString());		
		} catch(Exception e) {
				
		} finally {
				
		}
	}
	
	@RequestMapping("/getData")
	public void getData(HttpServletResponse res) {
		
	}
	
	@RequestMapping("/setData")
	public void setData(HttpServletRequest req, HttpServletResponse res) {
		String type = req.getParameter("type");
		
		switch(type) {
			case "insert":
				List<HashMap<String, Object>> params = ws.getComment();
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				String nickname = req.getParameter("nickName");
				String comment = req.getParameter("comment");
				resultMap.put("nickname", nickname);
				resultMap.put("comment", comment);
				params.add(resultMap);
				System.out.println(nickname + " " + comment);
				break;

			case "update":
				break;
			case "delete":
				break;
			default:
				break;
		}
		
		if(type.equals("insert")) {
			
		}

//		List<HashMap<String, Object>> params = ws.getComment();
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		String nickName = req.getParameter("nickName");
//		String comment = req.getParameter("comment");
//		resultMap.put("nickName", nickName);
//		resultMap.put("comment", comment);
//		params.add(resultMap);
	}
	
}
