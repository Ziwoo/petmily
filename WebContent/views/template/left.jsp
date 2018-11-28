<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<ul class="menu">
    <c:if test="${empty user_id}">
	 	<li><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></li> 
	 	<li><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></li>
	</c:if>
</ul>