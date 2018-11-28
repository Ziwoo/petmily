<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>게시판 글수정</h2>
		<form action="update.do" method="post" enctype="multipart/form-data" id="update_form">
			<input type="hidden" name="rv_num" value="${board.rv_num}">
			<ul>
				<li>
					<label for="rv_subject">제목</label>
					<input type="text" name="rv_subject" id="rv_subject" value="${board.rv_subject}" maxlength="50">
				</li>
				<li>
					<label for="rv_content">내용</label>
					<textarea rows="5" cols="30" name="rv_content" id="rv_content">${board.rv_content}</textarea>
				</li>
				<li>
					<label for="rv_pet_picture">파일</label>
					<input type="file" name="rv_pet_picture" id="rv_pet_picture">
					<c:if test="${!empty board.rv_pet_picture}">
					<br>
					<span>(${board.rv_pet_picture}) 파일이 등록되어 있습니다.
					다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
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