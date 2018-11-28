<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
</head>
<body>
	<h2>아이디찾기</h2>
	<form action="findId.do" method="get" id="findId_form">
		<span>가입시 이름과 이메일을 적어주세요.</span>
		<ul>
			<li>
				<label for="user_name">회원이름</label>
				<input type="text" name="user_name" id="user_name"
				              size="8" maxlength="12">              
			</li>
			<li>
				<label for="user_email">이메일</label>
				<input type="email" name="user_email" 
				       id="user_email" maxlength="12">              
			</li>
		</ul>   
		<div class="align-center">
			<input type="submit" value="OK">
			<input type="button" value="홈으로">
		</div>
    </form>
</body>
</html>