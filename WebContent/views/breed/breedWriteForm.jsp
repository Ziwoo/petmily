<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분양</title>
<link rel="stylesheet" type="text/css" href="../css/style.css"><!-- 기본스타일 적용 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/breed.js"></script><!-- 유효성검사가 필요할경우 -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>

	<div class="page-main-style">
	<h2>분양 글작성</h2>
	<form action="${pageContext.request.contextPath}/breed/breedWrite.do" method="post" 
	          enctype="multipart/form-data" id="write_form">
		<ul>
			<li>
				<label for="br_subject">제목</label>
				<input type="text" name="br_subject" id="br_subject"
				              maxlength="40">                                    
			</li>
			<li>
				<label for="br_content">내용</label>
				<textarea rows="5" cols="30" 
					name="br_content" id="br_content"></textarea>             
			</li>
			<li>
				<label for="br_passwd">비밀번호</label>
				<input type="password" name="br_passwd" id="br_passwd"
				              maxlength="10">                                    
			</li>
			<li>
				<label for="br_pic">파일</label>
				<input type="file" name="br_pic" id="br_pic">              
			</li>
		</ul>   
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록" 
			       onclick="location.href='${pageContext.request.contextPath}/breed/breedList.do'">
		</div>                            
	</form>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>
