<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<style type="text/css">
	.button1{
		background-color:gray;
	}
</style>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>
	
	<div class="page-main-style">
		<h2>로그인</h2>
		<form action="login.do" method="post" 
	                               id="login_form">
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<input type="text" name="mem_id" id="mem_id"
				              size="8" maxlength="12">              
			</li>
			<li>
				<label for="mem_passwd">비밀번호</label>
				<input type="password" name="mem_passwd" 
				       id="mem_passwd" maxlength="12">              
			</li>
		</ul>
				<div class="align-center">
					<input type="submit" value="로그인"> <input type="button"
						value="홈으로" onclick="location.href='../main/main.do'"><br>
					
					회원아이디, 비밀번호를 잊어버리셨나요?
					<div class="button1" onclick="location.href='../member/findIdForm.do'">FIND ID</div>
					<div class="button1" onclick="location.href='../member/findPasswordForm.do'">FIND PASSWORD</div>
				
					회원가입을 하시면 더 많은 혜택이 있습니다.
					<div class="button1" onclick="location.href='../member/registerUserForm.do'">JOIN</div>
				</div>
			</form>
	</div>
		</div>                            
		</form>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>