package kr.gudi.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession session;
	
	@RequestMapping("/db")
	public String db() {
		String result = session.selectOne("test.select");
		System.out.println(result);
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {		
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String file(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) {
		try {
			int[] statusList = new int[files.length];
			for(int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				// 원본 파일명 생성 (test.txt)
				String originalFileName = file.getOriginalFilename();
				// 파일 확장자 생성 (test.txt -> .txt)
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
				// 고유한 파일명 생성 (UUID)
				String fileName = UUID.randomUUID().toString();
				// 프로젝트 경로 받기
				System.out.println(req.getContextPath());  // 화면에서만 사용 하면 좋다
				System.out.println(req.getSession().getServletContext().getRealPath("/")); // 파일 처리 시 좋다.
				// 데이터 가져오기
				byte[] data = file.getBytes();
				// 저장 경로 + 파일명 정의
				String realPath = req.getSession().getServletContext().getRealPath("/");
				String path = realPath + "resources\\upload\\"; // 작성자 / 메뉴 / 날짜 / 시간 / 파일명
				// 파일 객체 생성
				File f = new File(path);
				// 파일 생성 경로에 폴더가 없으면 생성 처리
				if(!f.isDirectory()) {
					f.mkdirs();
				}
				// 출력 객체 생성 + 파일 객체 넣기   (저장경로 + uuid + 확장자)
				OutputStream os = new FileOutputStream(new File(path + fileName + ext));
				// 가져온 데이터 출력 객체에 넣기
				os.write(data);
				// 출력 객체 종료
				os.close();
				
				FilesBean fb = new FilesBean();
				fb.setDelYn("N");
				fb.setFileName(originalFileName);
				fb.setFileURL(fileName + ext);
				int status = session.insert("test.insert",fb);
				statusList[i] = status;
				System.out.println(i + ") "+ originalFileName + " : " + status);
				System.out.println(fb.toString());
			}
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//			InputStream is = file.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			StringBuilder sb = new StringBuilder();
//			String txt = "";
//			String result = "";
//			while((txt = br.readLine()) != null) result += txt + "\n";
//			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping(value = "/download/{originalName}/{ext}") 
	public void download(HttpServletRequest req, HttpServletResponse res, @PathVariable("originalName") String originalFileName,
																		  @PathVariable("ext") String ext) {
		String path = "D:\\IDE\\workspace\\Resources\\";
		String fileName = "f16bff62-705f-4202-9df5-8c679b605c10.txt";
//		String originalFileName = "test.txt";
		try {
			InputStream input = new FileInputStream(path + fileName);
			OutputStream output = res.getOutputStream();
			IOUtils.copy(input, output);
			
			/**********************************************************************************************
			 * Header 정의
			 * Content-Type : 전송되는 Content가 어떤 유형인지 알려주는 목적을 가지고 있습니다.
			 * Content-Disposition : Content가 어떻게 처리되어야 하는지 나타냅니다.
			 *  > attachment : 브라우저는 해당 Content를 처리하지 않고, 다운로드하게 됩니다.
			 *  > inline: 브라우저가 Content를 즉시 출력해야 함을 나타냅니다.
			 **********************************************************************************************/
			
			res.setHeader("Content-Disposition", "attachment; filename=\""+ originalFileName+ "." + ext + "\"");
			
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
