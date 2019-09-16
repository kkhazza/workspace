<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/join" method="POST">
	   <lable for="nm">이름: </lable>
	   <input name="nm" id="nm" type="text" placeholder="이름을 입력하세요"><br>
	   <lable for="id">아이디: </lable>
	   <input name="id" id="id" type="text" placeholder="아이디를 입력하세요"><br>
	   <label for="pw">비밀번호: </label> 
	   <input name="pw" id="pw" type="password" placeholder="비밀번호를 입력하세요"><br>
	   <button type="submit">회원가입</button>
	 </form>
	 <a href="/notice">로그인창으로</a>		 
</body>
</html>