<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<script>
		function file_Event(obj){
			console.log(obj.files);
			var dt = new DataTransfer();
			for (var file of obj.files) {
				var fileName = file.name;
				var ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
				if("txt" == ext) {
					dt.items.add(file);
				}
			}
			obj.files = dt.files;
			console.log(obj.files);
		}
	</script>
</head>
<body>
	<h1>Hello world!</h1>
	<form action="" method="post" enctype="multipart/form-data">
		<input type="file" name="file" id="file" onchange="file_Event(this)" multiple="multiple"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>
