<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--  viewport 메타태그에 user-scalable=no 를 추가하여 모바일 기기에서 확대/축소 기능을 끌 수 있다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원정보수정</title>
<!-- 부트스트랩 css스타일이 필요한 경우 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<!-- 유효성검사가 필요할경우 -->
<script type="text/javascript" src="../js/member.js"></script>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="../js/jquery-3.1.1.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>
	<br><br>

	<div class="page-main-style">
		<h2>회원정보수정</h2>
		<form action="modifyUser.do" method="post" id="modify_form">
			<ul>
				<li>
					<label for="mem_name">이름</label> 
					<input type="text" name="mem_name" id="mem_name" 
						value="${p_member.mem_name}" maxlength="10">
				</li>
				<li>
					<label for="mem_passwd">비밀번호</label> 
					<input type="password" name="mem_passwd" 
						id="mem_passwd" maxlength="12">
				</li>
				<li>
					<label for="mem_cell">전화번호</label> 
					<input type="text"
						name="mem_cell" id="mem_cell" value="${p_member.mem_cell}"
						maxlength="15"></li>
				<li>
					<label for="mem_email">이메일</label> 
					<input type="email" name="mem_email" id="mem_email" 
						value="${p_member.mem_email}" maxlength="30">
				</li>
				<li>
					<label>우편번호</label> 
					<input type="text" name="mem_zipcode1" id="mem_zipcode1"
					value="${p_member.mem_zipcode1}" maxlength="6">-
					<input type="text" name="mem_zipcode2" id="mem_zipcode2"
					value="${p_member.mem_zipcode2}" maxlength="6">
				</li>
				<li>
					<label for="mem_addr1">주소</label> 
					<input type="text" name="mem_addr1" id="mem_addr1" 
						value="${p_member.mem_addr1}" maxlength="13">
				</li>
				<li>
					<label for="mem_addr2">나머지 주소</label> 
					<input type="text" name="mem_addr2" id="mem_addr2" 
						value="${p_member.mem_addr2}" maxlength="30">
				</li>
			</ul>
				`
			<div class="align-center">
				<input type="submit" value="수정"> <input type="button"
					value="홈으로" onclick="location.href='../main/main.do'">
			</div>
		</form>
	</div>
	
	<br><br>
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>