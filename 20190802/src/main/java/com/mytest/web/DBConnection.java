package com.mytest.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class DBConnection {
	
	public DBConnection() {
		if(classCheck()){
			if(getConn()) {
				System.out.println("접속 완료");
			}else {
				System.out.println("접속 오류");
			}
		}else {
			System.out.println("접속 불가");
		}
	}
	
	// 데이터 베이스 접속 정보 4가지
	String driver = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mysql://gdj16.gudi.kr:53306/201907";
	String user = "m12";
	String password = "m12";
	
	
	
	public Connection conn = null; //public 으로 다른클래스에서도 사용할수 있게 만들어줌
	PreparedStatement pstmt = null;	
	ResultSet rs = null; //excuteQuery() 메소드는 결과로 ResultSet을 반환하기 때문에 ResultSet을 저장할 rs를 선언 
	String sql = "select 1";
	List<Map<String, Object>> resultList = null;
	
	// 클래스 유무 확인 (org.mariadb.jdbc.Driver)가 있는지 확인 부분!!
		public boolean classCheck() {
			try {
				Class.forName(driver); //클래스 존재 유무 확인!!! class.forname()을 쓰면 driver 동작과 동시에 jdbc의 driverManager에 해당 드라이브를 등록시킴
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}	

		public boolean getConn() {
			try {
				conn = DriverManager.getConnection(url, user, password); //driver를 받아와서 실행하기 때문에 drivermanager를 사용한다
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
}
