package com.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession session;
	
	//DB연결 Test
	@RequestMapping(value = "/test")
	public String home(HttpServletRequest req) {
		int cnt = session.selectOne("test.test");
		req.setAttribute("cnt", cnt);
		return "home";
	}
	
	@RequestMapping(value = "/")
	public String test(Model m, Bean b) {
		m.addAttribute("data",session.selectList("test.select", b));
		return "test";
	}
	
//	@RequestMapping(value = "/search")
//	public String search(HttpServletRequest req) {
//		List<Bean> data = session.selectList("test.search", req.getParameter("search"));
//		System.out.println(req.getParameter("search"));
//		req.setAttribute("data", data);		
//		return "test";
//	}
	
}
