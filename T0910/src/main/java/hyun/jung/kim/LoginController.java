package hyun.jung.kim;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	SqlSession s;
	
	// Login 화면
	@RequestMapping(value="/")
	public String Loginpage() {
		return "Login";
	}
	
	// Login 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String Login(LoginBean lb, HttpSession hs) {		
		List<LoginBean> loginlist = s.selectList("test.selectlogin");
		for(int i = 0; i < loginlist.size(); i++) {
			if(lb.getId().equals(loginlist.get(i).getId()) && lb.getPw().equals(loginlist.get(i).getPw())) {
//				hs.setMaxInactiveInterval(1800); 세션에 남는 기록 시간 설정
				hs.setAttribute("id", loginlist.get(i).getId());
				return "redirect:notice";
			}
		}	
		return "redirect:/";
	}
	
	// Logout
	@RequestMapping(value="/logout")
	public String Logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 화면
	@RequestMapping(value="/joinpage")
	public String Joinpage() {
		return "Join";
	}
	
	// 회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String Join(LoginBean lb) {
		int idcheck = s.selectOne("test.idcheck",lb);
		if(idcheck == 1) {
			System.out.println("ID중복");
			return "redirect:joinpage";
		}
		s.insert("test.insertlogin", lb);
		return "redirect:/";
	}
	
	// 회원정보 자세히
	@RequestMapping(value="/joinresult")
	public String Joinresult(LoginBean lb, HttpSession hs, HttpServletRequest req) {
		if(hs.getAttribute("id") == null) {
			return "redirect:/";
		}
		req.setAttribute("loginlist", s.selectList("test.selectlogin"));
		req.setAttribute("id", hs.getAttribute("id"));
		return "Joinresult";
	}
	
	// 회원정보 수정
	@RequestMapping(value="/joinedit")
	public String Joinedit(LoginBean lb) {
		s.insert("test.editlogin", lb);
		return "redirect:notice";
	}
	
	// 회원정보 탈퇴
	@RequestMapping(value="/joindelete/{a}")
	public String Joindelete(LoginBean lb,@PathVariable("a") String a) {
		// 관리자가 탈퇴
		if(a.equals("admin")) {
			s.insert("test.deletelogin", lb);
			return "redirect:/admin";
		} else { // 회원이 직접 탈퇴
			s.insert("test.deletelogin", lb);
		}
		return "redirect:/";
	}
	
	// 관리자 페이지
	@RequestMapping(value="/admin")
	public String Admin(HttpServletRequest req){
		List<LoginBean> lb = s.selectList("test.admin");
		req.setAttribute("lb", lb);
		return "Admin";
	}	
	
	// 관리자가 승인
	@RequestMapping(value="/approval")
	public String Approval(LoginBean lb) {
		s.insert("test.approval", lb);
		return "redirect:/admin";
	}
	
	// 게시판 화면
	@RequestMapping(value="/notice")
	public String Notice(NoticeBean nb, HttpServletRequest req, HttpSession hs) {
		if(hs.getAttribute("id") == null) {
			return "redirect:/";
		}
		req.setAttribute("data", s.selectList("test.select", nb));
		return "Notice";
	}
	
	// 글쓰기 화면
	@RequestMapping(value="/insertpage")
	public String Insertpage(HttpSession hs) {
		if(hs.getAttribute("id") == null) {
			return "redirect:/";
		}
		return "Insertpage";
	}
	
	// insert
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String Insert(NoticeBean nb) {
		s.insert("test.insert", nb);
		return "redirect:notice";
	}
	
	// 자세히보기
	@RequestMapping(value="/result/{index}")
	public String Result(@PathVariable("index") int index, HttpServletRequest req, HttpSession hs) {
		if(hs.getAttribute("id") == null) {
			return "redirect:/";
		}
		req.setAttribute("data", s.selectList("test.result", index));
		return "Result";
	}
	
	// 수정
	@RequestMapping(value="/edit")
	public String Edit(NoticeBean nb) {
		s.insert("test.edit", nb);
		return "redirect:notice";
	}
	
	// 삭제
	@RequestMapping(value="/delete")
	public String Delete(NoticeBean nb) {
		s.insert("test.delete", nb);
		return "redirect:notice";
	}
}
