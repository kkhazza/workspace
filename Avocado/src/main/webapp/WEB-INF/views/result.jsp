<%@page import="kr.gudi.web.SelectBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>자세히보기</title>
</head>
<body>
	<h1>Download</h1>
<% List<SelectBean> data = (List<SelectBean>) request.getAttribute("data");
   if(data == null) {
	   System.out.println(":(");
	} else {
%>
	<p><%=data.get(0).getTitle() %></p>
<% 
	for(int i = 0; i < data.size(); i++){
%>
	<img src="<%=data.get(i).getFileurl() %>" onclick="download(<%=i %>)">
<% 
		} 
	}
%>
	<p><%=data.get(0).getComment() %></p>
	
<form action="/delete" method="post">
	<input type="hidden" name="no" id="no">
	<button type="submit">삭제</button>
</form>

	<p><a href='/notice'> 목록으로 </a></p>
</body>
<script>
	var params = document.location.href.split("http://gdj16.gudi.kr:20009/result/");
	var no = params[1]; 
	document.getElementById("no").value = no;
	function download(index) {
		location.href="/download/"+no+"/"+index;
	}
</script>
</html>