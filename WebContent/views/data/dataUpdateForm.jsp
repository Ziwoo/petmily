<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫정보글 수정</title>
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
	<h2>분양글 수정</h2>
	<form action="dataUpdate.do" method="post" 
	          enctype="multipart/form-data" id="update_form">
	    <input type="hidden" name="pd_num" value="${data.pd_num}">
		<ul>
			<li>
				<label for="pd_title">제목</label>
				<input type="text" name="pd_title" id="pd_title"
				       value="${data.pd_title}" maxlength="50">                                    
			</li>
			<li>
				<label for="pd_content">내용</label>
				<textarea rows="5" cols="30" 
					name="pd_content" id="pd_content">${data.pd_content}</textarea>             
			</li>
			<li>
				<label for="pd_passwd">비밀번호</label>
				<input type="password" name="pd_passwd" id="pd_passwd"
				       value="${data.pd_passwd}" maxlength="10">                                    
			</li>
			<li>
				<label for="pd_pic">파일</label>
				<input type="file" name="pd_pic" id="pd_pic">
				<c:if test="${!empty data.pd_pic}">
				<br>
				<span>(${data.pd_pic})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>	
				</c:if>              
			</li>
		</ul>   
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록" 
			       onclick="location.href='${pageContext.request.contextPath}/data/dataList.do'">
		</div>                            
	</form>
	</div>

	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>
