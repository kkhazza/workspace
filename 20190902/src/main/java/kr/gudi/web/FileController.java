package kr.gudi.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@RequestMapping("/file")
	public String file() {
		return "file";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String file(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) {
		try {
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
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "file";
	}
	
}
