<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항상세</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>공지사항상세</h2>
		<ul>
			<li>글번호 : ${notice.nb_num}</li>
			<li>제목 : ${notice.nb_subject}</li>
			<li>작성자 : ${notice.mem_id}</li>
			<li>조회수 : ${notice.nb_readcount}</li>
		</ul>
		<hr size="1" noshade width="100%">
		<div class="align-center">
			<c:if test="${!empty notice.filename }">
				<img src="../upload/${notice.filename}" class="detail-img">
			</c:if>
		</div>
		<p>
			${notice.nb_content}
		</p>
		<div class="align-right">
			등록날짜 : ${notice.nb_reg_date}
			<input type="button" value="수정" onclick="location.href='updateForm.do?nb_num=${notice.nb_num}'"
				<c:if test="${user_id != notice.mem_id}">disabled="disabled"</c:if>
			>
			<input type="button" value="삭제" onclick="location.href='delete.do?nb_num=${notice.nb_num}'"
				<c:if test="${user_id != notice.mem_id}">disabled="disabled"</c:if>
			>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</div>
</body>
</html>