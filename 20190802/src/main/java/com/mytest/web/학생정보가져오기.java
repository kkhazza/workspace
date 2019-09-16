package com.mytest.web;


	import java.io.BufferedReader;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


	public class 학생정보가져오기 {
		
		public boolean Run(String addr) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 학생정보 받아오기 시작.");
			try {
				// URL 정보 만들기
				URL url = new URL(addr);
				// URL 정보를 이용하여 접속하기
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				// 접속 시 요청 정보 정의하기 : GET 방식
				http.setRequestMethod("GET");
				// 응답 코드가 정상일때 제어문 실행 : 200(정상)
				if(http.getResponseCode() == 200) {//성공이면 code가 200번으로 오기때문에 200일때만
					// 응답 받은 결과값 입력 스트림 객체에 담기
					InputStream is = http.getInputStream();
					// 입력 스트림 객체의 값을 임시(버퍼 공간)저장 하기 
			        BufferedReader br = new BufferedReader(new InputStreamReader(is));
			        // 문자열 임시(버퍼 공간) 변수 생성
			        StringBuffer response = new StringBuffer();
			        // 입력 스트림에서 받아온 내용을 한줄씩 담기 위하여 사용할 문자열 변수 생성
			        String line = "";
			        // 각 행별로 문자열 추출하기 (반복문)
			        while ((line = br.readLine()) != null) {
			        	// 각 행의 문자열 값을 문자열 임시(버퍼 공간) 변수에 추가하기
			            response.append(line);
			            // 다음 행 처리를 하기 위하여 문자열 임시(버퍼 공간) 변수에 추가하기
			            response.append('\r');
			        }
			        // 입력 스트림 객체 종료하기
			        br.close();
			        System.out.println(response.toString());
			        
			        String strJson = response.toString(); //json 객체를 문자화 한것!
			        
			        JSONObject jObj = JSONObject.fromObject(strJson); //문자열 jsondata를 jsonObject로 바꿈
			        JSONArray students = jObj.getJSONArray("students");
			        System.out.println("행수: " + students.size()); //data가 배열로 들어있기때문에 size로 행의수 확인!
			        Map<String, Object> cols = null;
			        Map<String, Object> pointMap = null;
			        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			        for(int i = 0; i < students.size(); i++) {
			        	JSONObject row = students.getJSONObject(i);
			        	cols = new HashMap<String, Object>();
			        	cols.put("no", row.getString("no")); // no가 string이기 때문에 getString 으로 받음 (key, value로 구성되어있음)
			        	cols.put("nm", row.getString("nm"));
			        	/******************************************************************/
			        	//cols.put("point", row.getJSONObject("point"));
			        	pointMap = new HashMap<String, Object>();
			        	pointMap.put("Korean", row.getJSONObject("point").getString("Korean"));
			        	pointMap.put("Math", row.getJSONObject("point").getString("Math"));
			        	pointMap.put("English", row.getJSONObject("point").getString("English"));
			        	pointMap.put("Sceince", row.getJSONObject("point").getString("Science"));
			        	cols.put("point", pointMap);
			        	System.out.println(cols.toString());
			        	resultList.add(cols);
			        	/******************************************************************/
			        }
			        System.out.println(resultList.size());
			        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 학생정보 받아오기 종료.");
				} else {
					System.out.println("학생 정보를 받아 오지 못하였습니다.");
					return false;
				}
				// URL 접속 종료
				http.disconnect();
				
			} catch (Exception e) {
				// Try 내용 실행 중 오류 발생 시 오류 내용 Console 창에 출력하기
				e.printStackTrace();
				System.out.println("학생 정보를 받아 오지 못하였습니다.");
				return false;
			}
			
			return true;
		}
}
