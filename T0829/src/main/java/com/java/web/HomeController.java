package com.java.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession session;

	// DB연결 Test
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest req, HttpServletResponse res) {
		int cnt = (Integer) session.selectOne("test.test");
		System.out.println(cnt);
		req.setAttribute("cnt", cnt);
//		try {
//			res.getWriter().write("res test");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "test";
	}
	
	@RequestMapping(value = "/")
	public String select(Model m) {	
//		List<Bean> data = session.selectList("test.select"); // List로 만드는 이유는 쿼리결과가 여러개라서 selectList로 받기떄문
//		req.setAttribute("data", data);
		m.addAttribute("data", session.selectList("test.select"));
		return "home";
	}
	
	@RequestMapping(value = "/insert")
	public String insert(Bean b) {
		session.insert("test.insert", b); //  Bean형식으로 전달 (test.xml)
		return "redirect:/"; 
	}
	
	@RequestMapping(value = "/update")
	public String update(Bean b) {
		session.insert("test.update", b); //  Bean형식으로 전달 (test.xml)
		return "redirect:/"; 
	}
	
	
	@RequestMapping(value = "/delete")
	public String delete(Bean b) {
		session.insert("test.delete", b);
		return "redirect:/";
	}
	
}
