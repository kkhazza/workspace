package kr.gudi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	ListBean[] list = new ListBean[2];
	public HomeController() {
		list[0] = new ListBean(1, "JQuery 너무 어렵 ;)");
		list[1] = new ListBean(2, "LocalStorage 너무 좋아 :)");
	}
	
	@RequestMapping("/")
	public String home(HttpServletRequest req) {
		req.setAttribute("list", list);
		return "home";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req) {
		String txt = req.getParameter("txt");
		int size = list.length;
		ListBean[] temp = new ListBean[size + 1];
		for(int i = 0; i < size; i++) {
			temp[i] = list[i];
		}
		temp[size] = new ListBean(temp.length, txt);
		list = temp;
		return "redirect:/";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest req) {
		String index = req.getParameter("index");
		String txt = req.getParameter("txt");
		list[Integer.parseInt(index)].setTxt(txt);
		return "redirect:/";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req) {
		int index = Integer.parseInt(req.getParameter("index"));
		int size = list.length;
		ListBean[] temp = new ListBean[size - 1];
		int tempIndex = 0;
		for(int i = 0; i < size; i++) {
			if(index == i) continue;
			temp[tempIndex++] = list[i];
		}
		list = temp;
		return "redirect:/";
	}
	
}
