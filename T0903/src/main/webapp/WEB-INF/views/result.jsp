<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Download</h1>
<%
// 	String url = (String) request.getAttribute("url");
	String[] urls = (String[]) request.getAttribute("urls");
	for(int i = 0; i < urls.length; i++){
%>
	<img src="<%=urls[i] %>">
<% 
	}
%>
	<p><a href='/'> 다시 업로드 하기 </a></p>
</body>
</html>