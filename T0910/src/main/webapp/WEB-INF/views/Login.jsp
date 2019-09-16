<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function jp() {
	location.href="/joinpage"
}
</script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	 <h1>로그인</h1>
	 <form action="/login" method="POST">
	   <lable for="id">아이디: </lable>
	   <input name="id" id="id" type="text" placeholder="아이디를 입력하세요"><br>
	   <label for="pw">비밀번호: </label> 
	   <input name="pw" id="pw" type="password" placeholder="비밀번호를 입력하세요"><br>
	   <button type="submit">로그인</button>
	   <button type="button" onclick="jp()">회원가입</button>
	 </form>	 
</body>
</html>