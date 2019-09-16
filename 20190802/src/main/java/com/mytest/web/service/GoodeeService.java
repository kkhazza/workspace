package com.mytest.web.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.web.dao.GoodeeDao;

@Service
public class GoodeeService {
	
	String addr = "http://gdu.co.kr/process/process_010100.html";
	
	public void getHTML() {
		try {
			URL url = new URL(addr);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			if(http.getResponseCode() == 200) {
				InputStream is = http.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuffer response = new StringBuffer();
				String line = "";
				while ((line = br.readLine()) != null) {
		            response.append(line);
		            response.append('\r');
		        }
				br.close();
				String txt = response.toString().replace("\t", "");
				//System.out.println(txt);
				// 패턴 생성
				// (<[a-z].*[>])(.*?)(</?[a-z]*>)
				// (<div.+class=\"process-list\".*>)([\\s\\S]+?)(</?div*>)
				Pattern pattern = Pattern.compile("(<ul.+class=\"clearfix\".*>)([\\s\\S]+?)(</?ul*>)");
				// 대상 문자열에 패턴 적용
				Matcher matcher = pattern.matcher(txt);
				// 패턴 분석 시작
				// 분석 결과값 출력
				while (matcher.find()) {
					System.out.println("===========================1");
					//System.out.println(matcher.group(1));
		            System.out.println(matcher.group(2));
		            //System.out.println(matcher.group(3));
		            System.out.println("===========================2");
		        }
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void getHTMLParser() {
		try {
			Document doc = Jsoup.connect(addr).get();
			Elements newsHeadlines = doc.select(".process-list .first_li");
			for (Element headline : newsHeadlines) {
				String tit = headline.select(".tit").text();
				String date = headline.select(".date").text();
				String instructor = headline.select(".name").text();
				System.out.println(tit);
				System.out.println(date);
				System.out.println(instructor);
				System.out.println("====================================================");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		}
	
	
	@Autowired
	GoodeeDao gd;
	public void getTest() {
		System.out.println("시작!");
		int tot = gd.getTestList().size();
		System.out.println("총 행 수 : " + tot);
		HashMap<String, Object> resultMap =  gd.getTestOne();
		System.out.println(resultMap.toString());
		System.out.println("종료!");
	}

}


