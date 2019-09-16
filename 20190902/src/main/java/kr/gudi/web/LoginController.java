package kr.gudi.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	
	@Autowired
	SqlSession session;
	
	/*************************************************************
	 * 카카오 로그인
	 * 1) 카카오 사이트 권한 요청 > code
	 * 2) 카카오 사용자 토큰 발행 요청 > token
	 * 3) 카카오 사용자 정보 요청 > 해당 token 사용자 정보 받기
	 *************************************************************/
	
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse res) {		
		try {
			String url = "https://kauth.kakao.com/oauth/authorize";
			url += "?client_id=6bdb6d1cf2937cc33669a6cc969f40ca&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20009/Avocado", "UTF-8");
			url += "&response_type=code";
			System.out.println(url);
			res.sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/Avocado")
	public String Avocado(HttpServletRequest req, HttpServletResponse res) {		
		try {
			System.out.println("Avocado");
			String code = req.getParameter("code");
			System.out.println(code);
			String url = "https://kauth.kakao.com/oauth/token";
			url += "?client_id=6bdb6d1cf2937cc33669a6cc969f40ca&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20009/Avocado", "UTF-8");
			url += "&code=" + code;
			url += "&grant_type=authorization_code";
			System.out.println(url);
			HashMap<String, Object> resultMap = HttpUtil.getUrl(url);
			System.out.println(resultMap);
			
			String userUrl = "https://kapi.kakao.com/v2/user/me";
			userUrl += "?access_token=" + resultMap.get("access_token");
			System.out.println(userUrl);
			resultMap = HttpUtil.getUrl(userUrl);
			System.out.println(resultMap.get("properties"));
			JSONObject name = JSONObject.fromObject(resultMap.get("properties"));
			System.out.println(name.get("nickname"));

			
			
/*			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			int resCode = conn.getResponseCode();
			if(resCode == 200) {
				InputStream input = conn.getInputStream();
				InputStreamReader inputReader = new InputStreamReader(input, "UTF-8");
				BufferedReader br = new BufferedReader(inputReader);
				String line = "";
				String result = "";
				while((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println(result);
				JSONObject jObject = JSONObject.fromObject(result);
				String access_token = jObject.getString("access_token");
				System.out.println(access_token);
				input.close();
				
				String userUrl = "https://kapi.kakao.com/v2/user/me";
				userUrl += "?access_token=" + access_token;
				System.out.println(userUrl);
				
				u = new URL(userUrl);
				conn = (HttpURLConnection) u.openConnection();
				conn.setRequestMethod("POST");
				resCode = conn.getResponseCode();
				if(resCode == 200) {
					input = conn.getInputStream();
					inputReader = new InputStreamReader(input, "utf-8");
					br = new BufferedReader(inputReader);
					line = "";
					result = "";
					while((line = br.readLine()) != null) {
						result += line;
					}
					System.out.println(result);
				}
				input.close();
*/		
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:notice";
	}
	
	// 게시판 메인 페이지
	@RequestMapping("/notice")
	public String notice() {
		String test  = session.selectOne("Avocado.select");
		System.out.println(test);
		return "notice";
	}
	
	// 추가하는 페이지로 이동
	@RequestMapping("/input")
	public String input() {
		return "input";
	}
	
	// data insert
	@RequestMapping("/insert")
	public String insert() {
		
		return "redirect:notice";
	}
	
}
