<%@page import="hyun.jung.kim.LoginBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
 ul {
 margin: 10px 0px;
 padding: 0px;	
 width: 1006px;
 }
 li {
 float:left;
 list-style: none;
 text-align: center;
 border-bottom:solid 1px gray;
 border-right:solid 1px gray;
 }
 .w125{width: 125px;}
 .w250{width: 250px;}
</style>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<h1>회원목록</h1>
<form>
<input type="hidden" name="no" id="no">
<button type="submit" style="display: none;" id="app" formaction="/approval">승인</button>
<button type="submit" style="display: none;" id="sec" formaction="/joindelete/admin">탈퇴</button>
</form>
<ul>
<li class="w125">선택</li>
<li class="w125">번호</li>
<li class="w250">이름</li>
<li class="w250">ID</li>
<li class="w125">승인여부</li>
<li class="w125">탈퇴여부</li>
</ul>
<ul>
<%
boolean a = false;
boolean d = false;
List<LoginBean> lb = (List<LoginBean>) request.getAttribute("lb");
for(int i = 0; i < lb.size(); i++){
	if(lb.get(i).getId().equals("admin")){
		continue;
	}
	if(lb.get(i).getAppYn().equals("N")){
		a = true;
	} else {
		d = false;
	}
	if(lb.get(i).getDelYn().equals("N")){
		d = true;
	} else {
		d = false;
	}
%>
<li class="w125"><input type="checkbox" onclick="checkEvent(<%=lb.get(i).getNo()%>,<%=a %>,<%=d %>)" name="checkbox"></li>	
<li class="w125"><%=lb.get(i).getNo()-1 %></li>
<li class="w250"><%=lb.get(i).getNm() %></li>
<li class="w250"><%=lb.get(i).getId() %></li>
<li class="w125"><%=lb.get(i).getAppYn() %></li>
<li class="w125"><%=lb.get(i).getDelYn() %></li>
<%
}
%>
</ul>
<ul><a href="/notice">홈으로</a></ul>
</body>
<script type="text/javascript">
var checkIndex = -1;
function checkEvent(index,a,d){
	var ci = index - 2; // 관리자 빼고 2번부터 시작이므로 checkbox의 배열 첫번째[0]가 되려면 inedx에서 2를 빼야함
	var check = document.getElementsByName("checkbox");			
	// 체크박스 전체 해제 처리
	for(var i = 0; i < check.length; i++){
		check[i].checked = false;				
	}
	// 체크박스가 다른것이 선택 되었을때 체크 
	if(ci != checkIndex) {
		check[ci].checked = true;
		document.getElementById("no").value = index;
		if(a == true){
			document.getElementById('app').style.display = 'inline-block';
		} else {
			document.getElementById('app').style.display = 'none';
		}
		if(d == true){
			document.getElementById('sec').style.display = 'inline-block';
		} else {
			document.getElementById('sec').style.display = 'none';
		}	
	} else {
		document.getElementById('app').style.display = 'none';
		document.getElementById('sec').style.display = 'none';
	}
	
	// 체크박스 On & Off (자기 자신를 두번 눌렀을때 발생)
	if(check[ci].checked) checkIndex = ci;
	else checkIndex = -1;
}
</script>
</html>