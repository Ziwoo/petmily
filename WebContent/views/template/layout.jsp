<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- ====================view page 설정==================== --%>
<c:set var="viewPage">
	<jsp:include page="${viewURI}"></jsp:include>
</c:set>
<%-- ====================view page 설정==================== --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Petmilly에 오신걸 환영합니다.</title>
<%-- ====================css, javascript 설정==================== --%>
<c:out value="${resources}" escapeXml="false"/>
<%-- ====================css, javascript 설정==================== --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fotorama.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/petstyle.css" />

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.slides.min.js"></script>
</head>
<body>
	<div class="container" id="main">
		<div id="main_header">
			<jsp:include page="header.jsp"/>
		</div>
		<%-- <div id="main_menu">
			<jsp:include page="left.jsp"/>
		</div> --%><!-- 사용x -->
		<div id="main_body">${viewPage}</div>
		<div id="main_footer">
		    <jsp:include page="footer.jsp"/><!-- 스타일 적용x -->
		</div>
	</div>
</body>
</html>
