<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫정보 추가 글쓰기</title>
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
		<h2>펫정보 글쓰기</h2>
		<form action="${pageContext.request.contextPath}/data/dataWrite.do" method="post" 
				enctype="multipart/form-data" id="write_form">
			<ul>
				<li>
					<label for="pd_title">제목</label>
					<input type="text" name="pd_title" id="pd_title" maxlength="50">
				</li>
				<li>
					<label for="pd_content">내용</label>
					<textarea rows="5" cols="30" name="pd_content" id="pd_content"></textarea>
				</li>
					<li>
				<label for="pd_passwd">비밀번호</label>
				<input type="password" name="pd_passwd" id="pd_passwd"
				              maxlength="10">                                    
				</li>
				<li>
					<label for="pd_pic">파일</label>
					<input type="file" name="pd_pic" id="pd_pic">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록으로" onclick="location.href='../data/dataList.do'">
			</div>
		</form>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>