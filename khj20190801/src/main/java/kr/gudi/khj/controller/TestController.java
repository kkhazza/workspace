package kr.gudi.khj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gudi.khj.service.ServiceInterface;
import net.sf.json.JSONObject;

@Controller
public class TestController {
	
	@Autowired
	ServiceInterface si;
	
	@RequestMapping("/getData")
	public void getData(HttpServletResponse response) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(si.getData());
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getData2")
	public String getData2(Model model) {
		HashMap<String, Object> resultMap = si.getData();
		model.addAttribute("data", resultMap);
		return "json";
	}
	
	@RequestMapping("/setData")
	public String setData(Model model) {
		model.addAttribute("data", si.setData());
		return "json";
	}
		
}
