<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<ul>
		<li>글번호 : ${board.rv_num}</li>
		<li>제목 : ${board.rv_subject}</li>
		<li>작성자 : ${board.mem_id}</li>
		<li>조회수 : ${board.rv_readcount}</li>
	</ul>
	<hr size="1" noshade width="100%">
	<div class="align-center">
		<c:if test="${!empty board.rv_pet_picture}">
		<img src="${pageContext.request.contextPath}/upload/${board.rv_pet_picture}" class="detail-img">
		</c:if>
	</div>
	<p>
		${board.rv_content}
	</p>
	<div class="align-right">
		등록날짜 : ${board.rv_now_date}
	
		<input type="button" value="수정" onclick="location.href='updateForm.do?rv_num=${board.rv_num}'"
			<c:if test="${user_id != board.mem_id}">disabled="disabled"</c:if>>
		<input type="button" value="삭제" onclick="location.href='delete.do?rv_num=${board.rv_num}'"
			<c:if test="${user_id != board.mem_id}">disabled="disabled"</c:if>>

		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
	<%--
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="num" id="num" value="${board.num}">
			<input type="hidden" name="id" value="${user_id}" id="user_id">
			<textarea rows="3" cols="50" name="re_content" id="re_content" maxlength="300" class="rep-content"
			<c:if test="${empty user_id}">disabled="disbled"</c:if>
			><c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			<c:if test="${!empty user_id}">                            
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">
				</div>
			</c:if>
		</form>
	</div>
	--%>
	<!-- 목록 출력 -->
	<%-- 
	<div id="output"></div>
	<div class="paging_button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="../images/ajax-loader.gif">
	</div>
	--%>
	<!-- 수정폼 -->
	<%--
	<div id="modify_div" style="display:none;">
		<form id="mre_form">
			<input type="hidden" name="re_num" id="mre_num">
			<input type="hidden" name="id" id="muser_id">
			<textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content"></textarea>
			<div id="mre_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="mre_second" class="align-right">
				<input type="submit" value="수정">
				<input type="button" value="취소" class="re-reset">
			</div>
			<hr size="1" noshade width="96%">
		</form>
	</div>
	  --%>
</div>
</body>
</html>