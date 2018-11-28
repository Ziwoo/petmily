<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Insert title here</title>
<script src="../js/jquery-3.1.1.min.js"></script>
</head>
<body>
회원님의 아이디는 ${user_id}입니다.

<c:if test="${empty user_id}">
	<script>
		alert('회원등록정보가 불일치 합니다.');
		history.go(-1);
	</script>
</c:if>
</body>
</html>
