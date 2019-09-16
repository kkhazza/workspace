<%@page import="kr.gudi.web.PanelBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.gudi.web.ListBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Panel Test</title>
  <link rel="shortcut icon" type="image/x-icon" href="/web/resources/img/favicon.png">
  <link rel="stylesheet" href="/web/resources/css/panel.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
      <a class="navbar-brand" href="#">GUDI</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Stores</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">    
  <div class="row">
<%
	List<PanelBean> list = (List<PanelBean>) request.getAttribute("list");
	if(list == null){
		System.out.println("목록이 없음");
	} else {
		for(int i = 0; i < list.size(); i++){
%>    
		    <div class="col-sm-4">
		      <div class="panel panel-primary">
		        <div class="panel-heading"><%=list.get(i).getTitle() %></div>
		        <div class="panel-body"><img src="<%=list.get(i).getUrl() %>" class="img-responsive" style="width:100%" alt="Image"></div>
		        <div class="panel-footer"><%=list.get(i).getComment() %></div>
		      </div>
		    </div>
<%
		}
	}
%>      
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
  <form class="form-inline" action="" method="post">Get deals:
    <input type="text" name="title" class="form-control" size="50" placeholder="Search">
    <button type="submit" class="btn btn-info">Find</button>
  </form>
</footer>

</body>
</html>
