<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#comment {
	width: 500px;
}
</style>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	 <form action="/insert" method="POST">
	   <input type="hidden" name="id" id="id" value="${sessionScope.id}">
	   <lable for="title">제목: </lable>
	   <input name="title" id="title" type="text" placeholder="제목을 입력하세요"><br>
	   <label for="comment">내용: </label> 
	   <input name="comment" id="comment" type="text" placeholder="내용을 입력하세요"><br>
	   <button type="submit">글쓰기</button>
	 </form>
	 <a href="/notice">목록으로</a>		 
</body>
</html>