package kr.gudi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PanelController {

	@Autowired
	SqlSession session;	
	
	@RequestMapping("/panel")
	public String select(Model m, PanelBean pb) {
		// 데이터 가져 오기
//		String title = req.getParameter("title");
//		PanelBean pb = new PanelBean();
//		pb.setTitle(title);
		m.addAttribute("list", session.selectList("panel.select", pb));
		return "panel";
	}
		
}
