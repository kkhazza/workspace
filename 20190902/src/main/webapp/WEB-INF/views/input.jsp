<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.form-group {
		margin: 20px 0px;
	}
	input {
		width: 200px;
	}
	html,body {
		background: #cffac0;
	}
</style>
<title>추가</title>
</head>
<body>

	<div>
		<form action="/insert" method="post" enctype="multipart/form-data">
			<div class="form-group">
			  <label for="title">제목:</label>
			  <input type="text" class="form-control"  id="title" name="title" placeholder="제목을 입력하세요." autocomplete="on">
			</div>
			<div class="form-group">
	          <input type="file" name="file" id="file" onchange="file_Event(this)" multiple=multiple> 
			</div>
			<div class="form-group">
			  <label for="comment">내용:</label>
			  <input type="text" class="form-control" id="comment" name="comment" placeholder="내용을 입력하세요." autocomplete="on">
			</div>
			<button type="submit">추가</button>				
		</form>
	</div>
	
	
	
</body>
</html>