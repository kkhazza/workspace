<%@page import="com.java.web.Bean"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문제</title>
<link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.png">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script rel="stylesheet" href="/web/resources/css/home.css"></script>
<script>
var checkIndex = -1;
function chkEvent(index){
	var check = document.getElementsByName("chk") //div안에 checkbox목록 가져오기
	//일단 전체 다 해제!
	for(var i = 0; i < check.length; i++){
		check[i].checked = false;				
	}

	if(index != checkIndex){ //i(순서)랑 checkIndex(-1)랑 다를때
		check[index].checked = true;
		document.getElementsByName("no")[0].removeAttribute("disabled"); 
		var txt = document.getElementsByTagName("tr")[index + 1].getElementsByTagName("td")[2].textContent;
		var no = document.getElementsByTagName("tr")[index + 1].getElementsByTagName("td")[1].textContent;
		document.getElementById("text").value = txt;		
		document.getElementsByName("no")[0].value = no;
		document.getElementById("update").classList.remove('disabled');
		document.getElementById("delete").classList.remove('disabled');
	} else {
		document.getElementsByName("no")[0].disabled;
		document.getElementById("text").value = "";
		document.getElementsByName("no")[0].value = "";
		document.getElementById("update").classList.add('disabled');
		document.getElementById("delete").classList.add('disabled');
	}
	
	if(check[index].checked) checkIndex = index;
	else checkIndex = -1;
}
</script>
</head>
<body>
<div class="container">
	<h3>웹 문제</h3>
</div>
<div class="container">
	<h1 class="text-center">구디아카데미</h1>
	<form id="edit">
	  <div class="form-group row">
	    <div class="col-xs-2">
	    	<label for="text">한줄평  :</label>
	    </div>
	    <div class="col-xs-7">
	    	<input type="text" class="form-control" id="text" name="txt" placeholder="입력하세요." autocomplete="off">
	    	<input type="hidden" name="no" disabled="disabled"> 
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-primary" id="submit" formaction="/insert">추가</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-success disabled" id="update" formaction="/update">수정</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-danger disabled" id="delete" formaction="/delete">삭제</button>
	    </div>
	  </div>
	</form>
</div>
<div class="container">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>선택</th>
	      <th>번호</th>
	      <th>한줄평</th>
	    </tr>
	  </thead>
	  <tbody>
<%
	List<Bean> data = (List<Bean>) request.getAttribute("data");
	for(int i = 0; i < data.size(); i++) {
%>	
	<tr>
		<td><input type="checkbox" onclick="chkEvent(<%=i %>)" name="chk"> </td>
		<td><%= data.get(i).getNo() %></td>
		<td><%= data.get(i).getTxt() %></td>
	</tr>
<%
	}
%>
	  </tbody>
	</table>
</div>
<!-- Modal -->
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
              <input type="text" class="form-control" id="usrname" placeholder="Enter email" required="required" autocomplete="off">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="text" class="form-control" id="psw" placeholder="Enter password" required="required" autocomplete="off">
            </div>
              <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
          </form>
        </div>
      </div>
      
    </div>
  </div> 
</div>
</body>
</html>