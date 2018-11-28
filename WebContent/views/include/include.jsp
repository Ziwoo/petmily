<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 태그 사용시 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 기본</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"><!-- 기본스타일 적용 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script><!-- 유효성검사가 필요할경우 -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>
	
	<!-- 해당 jsp 내용 -->
	
	
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>