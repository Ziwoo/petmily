<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제폼</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"><!-- 기본스타일 적용 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/data.js"></script><!-- 유효성검사가 필요할경우 -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>

	<div class="page-main-style">
	<h2>게시글 삭제</h2>
	<form action="dataDelete.do" method="post" id="delete_form">
		<!-- 삭제할 게시글의 br_num을 삭제폼에 전달 -->
		<input type="hidden" name="pd_num" value="${pd_num}">
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				${user_id} <!-- session scope  에서 읽어옴-->
			</li>
			<li>
				<label for="pd_passwd">비밀번호</label>
				<input type="password" name="pd_passwd" id="pd_passwd" 
					maxlength="12">
			</li>
			<li>
				<label for="check_passwd">비밀번호 확인</label>
				<input type="password" name="check_passwd" id="check_passwd" 
					maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="삭제">
			<input type="button" value="목록" 
				onclick="location.href='${pageContext.request.contextPath}/data/dataList.do'">
		</div>
	</form>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>