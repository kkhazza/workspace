<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>File Upload</title>
    <link rel="stylesheet" href="/resources/css/file.css">
    <script>
    	var dt = new DataTransfer();
    	function formList(){
    		console.log(dt);
    	}
		function file_Event(obj){
			console.log(obj.files);
			for (var i = 0; i < obj.files.length; i++) {
				var fileName = obj.files[i].name;
				var ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
				console.log(fileName, ext);
				var text = "";
				if("jpg" == ext || "jpeg" == ext || "png" == ext) {
					text = "이미지";
				} else {
					continue;
				}
				var node = document.createElement("LI");
				var textnode = document.createTextNode(text);
				node.appendChild(textnode);
// 				node.classList.add("클래스명"); 클래스 추가
				document.getElementById("t").appendChild(node);
				dt.items.add(obj.files[i]);
			}
			obj.files = dt.files;
		}
	</script>
</head>
<body>
        <div class="container">
            <form action="/" method="post" enctype="multipart/form-data">
                <ul id="t">
                	<li>
	                    <label for="file" >+</label>
	                    <input type="file" name="file" id="file" onchange="file_Event(this)" multiple=multiple>    
                    </li>
        			<input type="submit" value="전송">                  
                </ul>
            </form>           
        </div>
    </body>
</html>