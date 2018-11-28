<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="align-right">
		<table id="login" align="right">
			<%-- <a href="${pageContext.request.contextPath}/board/list.do">게시판</a> 
	    <a href="${pageContext.request.contextPath}/main.do">홈으로</a> --%>
			<c:if test="${empty user_id}">
			<tr>
				<td>
					<h5><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인&nbsp;&nbsp;</a></h5>
				</td>
				<td>
					<h5><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입 </a></h5>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${!empty user_id}">
			<tr>
				<td>
					<h5><a href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보수정&nbsp;&nbsp;</a></h5>
				</td>
				<td>
					<h5><a href="${pageContext.request.contextPath}/member/deleteUserForm.do">회원탈퇴&nbsp;&nbsp; </a></h5>
				</td>
				<td>
				[${user_id}님 로그인 중]
				</td>
				<td>
					<h5><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃 </a></h5>
				</td>
			</tr>
			</c:if>
			
		</table>
	</div>
	
	<br>
	<br>

	<!-- 사이트 로고 -->
	<div align="center" class="header" id="logo">
		<a href="${pageContext.request.contextPath}/main/main.do"><img
			height="130" src="../images/logo3.png"></a>
	</div>

	<!-- 상단 메뉴 시작, 드롭박스 -->
	<nav class="nav navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"></a>
		</div>

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav nav-justified" align="center">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> HOME <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">회사소개</a></li>
						<li><a href="#">FamilySite</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 분양 <b class="caret"></b></a>
					<ul class="dropdown-menu" align="center">
						<li><a href="#">분양절차</a></li>
						<li><a href="#">펫 정보</a></li>
						<li><a href="${pageContext.request.contextPath}/breed/breedList.do">분양</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 커뮤니티 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">공지사항</a></li>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">분양후기</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 지점안내 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">지점안내</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</div>