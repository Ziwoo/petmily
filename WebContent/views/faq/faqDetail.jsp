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
			<li>글번호 : ${faq.faq_num}</li>
			<li>제목 : ${faq.faq_subject}</li>
			<li>작성자 : ${faq.mem_id}</li>
			<li>조회수 : ${faq.faq_readcount}</li>
		</ul>
		<hr size="1" noshade width="100%">
		<p>
			${faq.faq_content}
		</p>
		<div class="align-right">
			등록날짜 : ${faq.faq_reg_date}
			<input type="button" value="수정" onclick="location.href='updateForm.do?faq_num=${faq.faq_num}'"
				<c:if test="${user_id != faq.mem_id}">disabled="disabled"</c:if>
			>
			<input type="button" value="삭제" onclick="location.href='delete.do?faq_num=${faq.faq_num}'"
				<c:if test="${user_id != faq.mem_id}">disabled="disabled"</c:if>
			>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</div>
</body>
</html>