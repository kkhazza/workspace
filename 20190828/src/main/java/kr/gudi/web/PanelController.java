package kr.gudi.web;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanelController {

	@Autowired
	SqlSession session;	
	
	@RequestMapping("/panel")
	public String select(Model m) {
		// 데이터 가져 오기
		List<PanelBean> list = session.selectList("panel.select");
		m.addAttribute("list", list);
		return "panel";
	}
	
}
