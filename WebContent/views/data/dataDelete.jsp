<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
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
	<h2>글 삭제</h2>
	<div class="result-display">
		게시글이 삭제되었습니다.<br>
		<a href="${pageContext.request.contextPath}/data/dataList.do">목록</a>
	</div>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>
</c:if>

<c:if test="${!check}">
	<script>
		alert('비밀번호가 일치하지 않습니다.');
		history.go(-1);
	</script>
</c:if>