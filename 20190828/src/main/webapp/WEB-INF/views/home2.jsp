<%@page import="java.util.List"%>
<%@page import="kr.gudi.web.ListBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>문제</title>
	<link rel="shortcut icon" type="image/x-icon" href="/web/resources/img/favicon.png">
	<link rel="stylesheet" href="/web/resources/css/home.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script>
		var checkIndex = -1;
		function checkEvent(index){
			// 체크박스 목록 가져오기
			var check = document.getElementsByName("checkbox");			
			
			// 체크박스 전체 해제 처리
			for(var i = 0; i < check.length; i++){
				check[i].checked = false;				
			}
			
			// 체크박스가 다른것이 선택 되었을때 체크 
			if(index != checkIndex) {
				check[index].checked = true;
				var txt = document.getElementsByTagName("tr")[index + 1].getElementsByTagName("td")[2].textContent;
				var no = document.getElementsByTagName("tr")[index + 1].getElementsByTagName("td")[1].textContent;
				document.getElementById("text").value = txt;
				document.getElementsByName("no")[0].value = no;
				document.getElementById("update").classList.remove('disabled');
				document.getElementById("delete").classList.remove('disabled');
			} else {
				document.getElementById("text").value = "";
				document.getElementsByName("no")[0].value = "";
				document.getElementById("update").classList.add('disabled');
				document.getElementById("delete").classList.add('disabled');
			}
			
			// 체크박스 On & Off (자기 자신를 두번 눌렀을때 발생)
			if(check[index].checked) checkIndex = index;
			else checkIndex = -1;
		}
	</script>
</head>
<body>
<div class="container">
	<h1 class="text-center">구디아카데미</h1>
	<form id="edit">
	  <div class="form-group row">
	    <div class="col-xs-2">
	    	<label for="text">한줄평  :</label>
	    </div>
	    <div class="col-xs-7">
	    	<input type="text" class="form-control" id="text" name="txt" placeholder="입력하세요." autocomplete="off">
	    	<input type="hidden" name="no">
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-primary" id="submit" formaction="/insert">추가</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-success disabled" id="update" formaction="/update2">수정</button>
	    </div>
	    <div class="col-xs-1">
	    	<button type="submit" class="btn btn-danger disabled" id="delete" formaction="/delete2">삭제</button>
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
	List<ListBean> list = (List<ListBean>) request.getAttribute("list");
	if(list == null){
		System.out.println("없다");
	} else {
		for(int i = 0; i < list.size(); i++){
%>
			<tr>
		      <td><input type="checkbox" onclick="checkEvent(<%=i %>)" name="checkbox"> </td>
		      <td><%=list.get(i).getNo() %></td>
		      <td><%=list.get(i).getTxt() %></td>
		    </tr>
<%
		}
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