<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ쓰기</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/faq.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>FAQ쓰기</h2>
		<form action="write.do" method="post" id="write_form">
			<ul>
				<li>
					<label for="faq_subject">제목</label>
					<input type="text" name="faq_subject" id="faq_subject" maxlength="50">
				</li>
				<li>
					<label for="faq_content">내용</label>
					<textarea rows="5" cols="30" name="faq_content" id="faq_content"></textarea>
				</li>
				<li>
					<label for="faq_passwd">비밀번호</label>
					<input type="password" name="faq_passwd" id="faq_passwd" maxlength="20">
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