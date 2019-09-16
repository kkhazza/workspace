<%@page import="hyun.jung.kim.LoginBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<h1>회원정보</h1>
	<form action="/join" method="POST">
<% List<LoginBean> loginlist = (List<LoginBean>) request.getAttribute("loginlist");
   String id = (String) request.getAttribute("id");
   int no = 0;
   for(int i = 0; i < loginlist.size(); i++){
	   if(id.equals(loginlist.get(i).getId())){
		   no = i;
		   break;
	   }
   }
%>
		   <input type="hidden" name="no" id="no" value="<%=loginlist.get(no).getNo()%>">
		   <lable for="nm">이름: </lable>
		   <input name="nm" id="nm" type="text" placeholder="이름을 입력하세요" value="<%=loginlist.get(no).getNm()%>"><br>
		   <lable for="id">아이디: </lable>
		   <input name="id" id="id" type="text" placeholder="아이디를 입력하세요" value="<%=loginlist.get(no).getId()%>"><br>
		   <label for="pw">비밀번호: </label> 
		   <input name="pw" id="pw" type="password" placeholder="비밀번호를 입력하세요" value="<%=loginlist.get(no).getPw()%>"><br>
		   <button type="submit" formaction="/joinedit">수정</button>
		   <button type="submit" formaction="/joindelete/join">삭제</button>
	</form>	
	<a href="/notice">목록으로</a>	
</body>
</html>