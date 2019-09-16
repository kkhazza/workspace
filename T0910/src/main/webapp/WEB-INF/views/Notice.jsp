<%@page import="hyun.jung.kim.NoticeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script>
function ip() {
	location.href="/insertpage";
}
function result(index){
	location.href="/result/"+index;
}
</script>
  <title>게시판</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    .cl:hover {
      cursor:pointer;
      opacity: 0.5;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/notice">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <form class="navbar-form navbar-right" role="search" action="/notice" method="post"> 
        <div class="form-group input-group">
          <input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해주세요">
          <span class="input-group-btn">
            <button class="btn btn-default" type="submit">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>        
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
      	<li id="admin" style="display: none;"><a href="/admin"><span class="glyphicon glyphicon-user"></span>회원관리</a></li>
        <li><a href="/logout"><span class="glyphicon glyphicon-user"></span>Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-3 well">
      <div class="well">
        <a href="/joinresult">${sessionScope.id}</a>
      </div>
      <div class="well">
      	<button onclick="ip()">글쓰기</button>
      </div>
      
    </div>
    <div class="col-sm-7">
         
<% List<NoticeBean> data = (List<NoticeBean>)request.getAttribute("data"); 
	for(int i = 0; i < data.size(); i++){
%>	
	<div class="row cl" onclick="result(<%=data.get(i).getNo() %>)">
        <div class="col-sm-3">
          <div class="well">
           <p><%=data.get(i).getId() %></p>
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
          	<p style="border-bottom:1px solid black"><%=data.get(i).getTitle() %></p>
            <p><%=data.get(i).getComment() %></p>
          </div>
        </div>
      </div>   
<%	
	}
%>
      
        
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>김현정</p>
</footer>
</body>
<script type="text/javascript">
if(<%=session.getAttribute("id").equals("admin")%>){
	document.getElementById('admin').style.display = 'inline-block';
}
</script>
</html>