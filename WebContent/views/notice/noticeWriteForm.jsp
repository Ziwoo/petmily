<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항쓰기</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/notice.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>공지사항쓰기</h2>
		<form action="write.do" method="post" enctype="multipart/form-data" id="write_form">
			<ul>
				<li>
					<label for="nb_subject">제목</label>
					<input type="text" name="nb_subject" id="nb_subject" maxlength="50">
				</li>
				<li>
					<label for="nb_content">내용</label>
					<textarea rows="5" cols="30" name="nb_content" id="nb_content"></textarea>
				</li>
				<li>
					<label for="filename">파일</label>
					<input type="file" name="filename" id="filename">
				</li>
				<li>
					<label for="nb_passwd">비밀번호</label>
					<input type="password" name="nb_passwd" id="nb_passwd" maxlength="20">
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