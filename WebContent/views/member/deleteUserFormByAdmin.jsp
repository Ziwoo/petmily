<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"><!-- 기본스타일 적용 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>
	
	<div class="page-main-style">
	<h2>회원탈퇴</h2>
	<form action="deleteUserByAdmin.do?" method="get">
	    <input type="hidden" name="mem_id" value="${p_member.mem_id}">
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				
				${p_member.mem_id}

			</li>

		</ul>
		<div class="align-center">
			<input type="submit" value="회원탈퇴">
			<input type="button" value="홈으로" 
			       onclick="location.href='../main/main.do'">
		</div>                            
	</form>
	</div>

<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>