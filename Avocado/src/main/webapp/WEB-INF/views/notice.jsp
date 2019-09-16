<%@page import="kr.gudi.web.ListBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Avocado</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script>
  function inputlc(){
	  location.href="/input";
  }
  function result(index) {
	  location.href="/result/"+index;
  }
  </script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    #insert {
    	background: #ebffed;
    }
    #h:hover {
    	cursor: pointer;
    	opacity: 0.8;
    }
  </style>
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
  	<img src="/resources/img/Logo.jpg">
    <h1>사진 게시판</h1>      
    <p>당신의 사진을 공유해주세요</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Avocado</a>
    </div>

    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="#"><button type="button" onclick="inputlc()" id="insert">추가</button></a></li>
        <li><a href="/logout"><span class="glyphicon glyphicon-user"></span>Logout</a></li>
      </ul>
    </div>
  </div>
</nav>


<div class="container">    
  <div class="row">
 <% List<ListBean> list = (List<ListBean>) request.getAttribute("list"); 
 	if(list == null) {
 		System.out.println(":(");
 	} else { 
 	for(int i = 0; i < list.size(); i++){
 %>
    <div class="col-sm-4" id="h" onclick="result(<%=list.get(i).getNo()%>)">
      <div class="panel panel-primary">
        <div class="panel-heading"><%= list.get(i).getTitle() %></div>
        <div class="panel-footer"><%=list.get(i).getComment() %></div>
      </div>
    </div>
 <% 
 		} 
 	}
 %>       
  </div>
</div><br>


<footer class="container-fluid text-center">
  <p>아보카도(주) </p>
  <p>회장:김현정 부하들:김민정,진현욱</p>  
</footer>

</body>
</html>
