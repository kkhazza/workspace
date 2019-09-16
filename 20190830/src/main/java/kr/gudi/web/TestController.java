package kr.gudi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@Autowired
	SqlSession session;
	
	@RequestMapping("/test")
	public String test() {
		int cnt = (Integer) session.selectOne("test.test");
		System.out.println(cnt);
		return "test";
	}
	
	@RequestMapping("/select")
	public String select(HttpServletRequest req) {
		List<ListBean> list = session.selectList("test.select");
		req.setAttribute("list", list);
		return "home2";
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest req) {
		session.insert("test.insert", new ListBean(0, req.getParameter("txt")));
		return "redirect:/select";
	}
	
	@RequestMapping("/update2")
	public String update(HttpServletRequest req) {
		session.update("test.update", new ListBean(Integer.parseInt(req.getParameter("no")), req.getParameter("txt")));
		return "redirect:/select";
	}
	
	@RequestMapping("/delete2")
	public String delete(HttpServletRequest req) {
		session.update("test.delete", new ListBean(Integer.parseInt(req.getParameter("no")), req.getParameter("txt")));
		return "redirect:/select";
	}
	
}
