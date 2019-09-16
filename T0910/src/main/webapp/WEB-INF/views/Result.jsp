<%@page import="hyun.jung.kim.NoticeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%List<NoticeBean> data = (List<NoticeBean>)request.getAttribute("data");
  int no = data.get(0).getNo();
  String title = data.get(0).getTitle();
  String comment = data.get(0).getComment();
  boolean idcheck = false;
  if(session.getAttribute("id").equals(data.get(0).getId()) || session.getAttribute("id").equals("admin")){
	  idcheck = true;
  }
%>
<style type="text/css">
#comment {
	width: 500px;
}
</style>
<meta charset="UTF-8">
<title>자세히보기</title>
</head>
<body>
	<h1>자세히보기</h1>
	 <form method="POST">
	   <input type="hidden" name="no" id="no" value="<%=no %>">
	   <lable for="title">제목: </lable>
	   <input name="title" id="title" type="text" value="<%=title %>" placeholder="제목을 입력하세요" readonly="readonly"><br>
	   <label for="comment">내용: </label> 
	   <input name="comment" id="comment" type="text" value="<%=comment %>" placeholder="내용을 입력하세요" readonly="readonly"><br>
	   <button style="display: none;" id="edit" type="submit" formaction="/edit">수정</button>
	   <button style="display: none;" id="delete" type="submit" formaction="/delete">삭제</button>
	 </form>
	 <a href="/notice">목록으로</a>	 
</body>
<script>
var ic = <%=idcheck %>;
if (ic == true){
	document.getElementById('edit').style.display = 'inline-block';
	document.getElementById('delete').style.display = 'inline-block';
	document.getElementById('title').readOnly="";
	document.getElementById('comment').readOnly="";
}
</script>
</html>