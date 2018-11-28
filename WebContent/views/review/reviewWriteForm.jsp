<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>review</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>review</h2>
		<form action="write.do" method="post" enctype="multipart/form-data" id="write_form">
			<ul>
				<li>
					<label for="rv_subject">제목</label>
					<input type="text" name="rv_subject" id="rv_subject" maxlength="50">
				</li>
				<li>
					<label for="rv_content">내용</label>
					<textarea rows="5" cols="30" name="rv_content" id="rv_content"></textarea>
				</li>
				<li>
					<label for="rv_pet_picture">파일</label>
					<input type="file" name="rv_pet_picture" id="rv_pet_picture">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록으로" onclick="location.href='../board/list.do'">
			</div>
		</form>
	</div>
</body>
</html>