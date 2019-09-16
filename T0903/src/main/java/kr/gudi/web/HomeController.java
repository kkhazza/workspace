package kr.gudi.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {		
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String file(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) {
		try {
			String urls[] = new String[files.length]; 
			for(int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				String originalFileName = file.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
				String fileName = UUID.randomUUID().toString();
				System.out.println(req.getContextPath());  // 화면에서만 사용 하면 좋다
				System.out.println(req.getSession().getServletContext().getRealPath("/")); // 파일 처리 시 좋다.
				byte[] data = file.getBytes();
				String path = "D:\\downloads\\Apache24\\htdocs\\";
//				String realPath = req.getSession().getServletContext().getRealPath("/");
//				String path = realPath + "resources\\upload\\";
				File f = new File(path);
				OutputStream os = new FileOutputStream(new File(path + fileName + ext));
				os.write(data);
				os.close();				
				urls[i] = "http://192.168.3.90/" + fileName + ext; 
				System.out.println(urls);
//				String url= "http://192.168.3.90/" + fileName + ext; 
//				req.setAttribute("url", url);
			}
			req.setAttribute("urls", urls);						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "result";
	}
	

	
}
