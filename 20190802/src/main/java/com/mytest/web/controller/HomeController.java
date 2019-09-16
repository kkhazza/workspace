package com.mytest.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.web.학생정보가져오기;
import com.mytest.web.service.GoodeeService;
import com.mytest.web.service.HomeService;
import com.mytest.web.service.WebService;

import net.sf.json.JSONObject;


@Controller
public class HomeController {
	
	@Autowired
	HomeService hs;
	
	@Autowired
	WebService ws;
	
	public static List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
	
	@RequestMapping("user")
	public void user(HttpServletRequest req, HttpServletResponse res) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("no", HomeController.userList.size());
			HomeController.userList.add(userMap);			
			resultMap.put("rows", HomeController.userList);
			JSONObject jObj = JSONObject.fromObject(resultMap);
			res.getWriter().write(jObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET) // 주소와 전송방식을 정한다

	public String home() {
		
		Map<String,Object> map = hs.getData();
		
		return "home";
	}
	
	@RequestMapping(value = "/J", method = RequestMethod.GET) // 주소와 전송방식을 정한다

	public String jquery() {
		return "JQuery";
	}
	
	@RequestMapping("/getJson")
	public void getJson() {
		학생정보가져오기 학생 = new 학생정보가져오기();
		String addr ="http://localhost:8080/res/data/data.json";
		if(학생.Run(addr)) {
			System.out.println("OK!");
		}else {
			System.out.println("NO!");
		}
	}
	
	
	@RequestMapping("/getJDBC")
	public void getJDBC() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("NO");
		}
		System.out.println("End");
	}
	
	
	@Autowired
	GoodeeService gs;
	
	@RequestMapping("/getHTML")
	public void getHTML() {
		gs.getHTML();
	}
	
	@RequestMapping("/getHTML2")
	public void getHTMLParser() {
		gs.getHTMLParser();
	}
	
	@RequestMapping("/getTest")
	public void getTest() {
		gs.getTest();
	}
	
	@RequestMapping("/20190725")
	public String ex() {
		return "20190725";
	}
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	public void test(HttpServletRequest req, HttpServletResponse res) {
		try {
			HashMap<String, Object> UserMap = ws.getUser();
			System.out.println(UserMap.size());
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			String Check = (String) UserMap.get(id);
			
			try {
				if(Check.equals(pw)) {
					resultMap.put("nickname", id);
					resultMap.put("state", true);
				}
				
			} catch (Exception e) {
				resultMap.put("state", false);
			}
			
			//res.getWriter().write("test");
			System.out.println(id + " " +  pw);
		
			
			JSONObject jObj = JSONObject.fromObject(resultMap);
			res.getWriter().write(jObj.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
	@RequestMapping("/getData")
	public void getData(HttpServletResponse res) {
		
	}
	
	@RequestMapping("/setData")
	public void setData(HttpServletRequest req, HttpServletResponse res) {// 값이 아직 없으므로 set부터 구현한다
		String type = req.getParameter("type");
		switch(type) {
			case "insert":
				List<HashMap<String, Object>> params = ws.getcomment(); //메모리 주소를 가져와서 list에 저장하기 때문에 접근가능
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				String nickname = req.getParameter("nickname");
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
		}
		if(type.equals("insert")) {
			
		}
		
		
		
		
	}
	
}
