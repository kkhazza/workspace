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
		int cnt = (Integer) session.selectOne("test.test"); // 1.namespace 2.id
		System.out.println(cnt);
		return "test";
	}
	
	@RequestMapping("/")
	public String select(HttpServletRequest req) {
		List<ListBean> list = session.selectList("test.select");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getTxt());
		}
		req.setAttribute("list", list);
		return "home2";
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest req) {
		session.insert("test.insert", new ListBean(0, req.getParameter("txt"))); 
		return "redirect:/"; 
	}
	
	@RequestMapping("/update2")
	public String update(HttpServletRequest req) {
		session.insert("test.update", new ListBean(Integer.parseInt(req.getParameter("no")), req.getParameter("txt"))); 
		return "redirect:/"; 
	}
	
	@RequestMapping("/delete2")
	public String delete(HttpServletRequest req) {
		session.insert("test.delete", new ListBean(Integer.parseInt(req.getParameter("no")), req.getParameter("txt"))); 
		return "redirect:/"; 
	}
}
