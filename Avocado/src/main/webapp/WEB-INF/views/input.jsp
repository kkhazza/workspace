<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
var dt = new DataTransfer();
function file_Event(obj){
	for (var i = 0; i < obj.files.length; i++) {
		var fileName = obj.files[i].name;
		var ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
		var text = "";
		if("jpg" == ext || "jpeg" == ext || "png" == ext) {
			text = "이미지";
		} else {
			continue;
		}
		dt.items.add(obj.files[i]);
	}
	obj.files = dt.files;
}
</script>
<style>
	.form-group {
		margin: 20px 0px;
	}
    
	input {
		width: 300px;
	}
    
	html,body {
		background-color: #ebffed;
        color: #4e4e4e;
	}
	
	.but {
		border: none;
		background-color: rgb(199, 199, 199);
		width: 70px;
		height: 30px;
        font-weight: bold;
        color: #585858;
        cursor: pointer;
        margin-top: 30px;
	}
    
    .form-content {
        display: block;
        margin-top: 10px;
        width:50%;
        height: 200px;
    }
    
    label {
        font-weight: bold;
    }
    
    #file {
        display: block;
        margin-top: 10px;
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
			  <label for="comment">내용</label>
              <textarea class="form-content" id="comment" name="comment" placeholder="내용을 입력하세요." autocomplete="on"></textarea>    
			</div>
            
            <div class="form-group">
              <label for="file">첨부파일</label>
	          <input type="file" name="file" id="file" onchange="file_Event(this)" multiple=multiple> 
			</div>
			<button type="submit" class="but">추가</button>				
		</form>
	</div>
</body>
</html>