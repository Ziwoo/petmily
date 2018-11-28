<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항수정</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/notice.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>공지사항수정</h2>
		<form action="update.do" method="post" enctype="multipart/form-data" id="update_form">
			<input type="hidden" name="nb_num" value="${nb.nb_num}">
			<ul>
				<li>
					<label for="nb_subject">제목</label>
					<input type="text" name="nb_subject" id="nb_subject" value="${nb.nb_subject}" maxlength="50">
				</li>
				<li>
					<label for="nb_content">내용</label>
					<textarea rows="5" cols="30" name="nb_content" id="nb_content">${nb.nb_content}</textarea>
				</li>
				<li>
					<label for="filename">파일</label>
					<input type="file" name="filename" id="filename">
					<c:if test="${!empty nb.filename}">
					<br>
					<span>(${nb.filename})파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
				</li>
				<li>
					<label for="nb_passwd">비밀번호</label>
					<input type="password" name="nb_passwd" id="nb_passwd" value="${nb.nb_passwd}" maxlength="10">
				</li>
							
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록으로" onclick="location.href='../board/list.do'">
			</div>
		</form>
	</div>
</body>
</html>