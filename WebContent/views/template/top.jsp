<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
		
		<div id="login" align="right">
			<c:if test="${user_id.equals('sky')}">
				<a href="../member/userList.do">회원관리</a>
			</c:if>
			<c:if test="${empty user_id}">
				<span><a href="../member/registerUserForm.do">회원가입</a></span>
				<span><a href="../member/loginForm.do">로그인</a></span>
			</c:if>
			<c:if test="${!empty user_id}">
				<span>[${user_id}님 로그인 중...]</span>
				<span><a href="../member/modifyUserForm.do">회원정보수정</a></span>
				<span><a href="../member/deleteUserForm.do">회원탈퇴</a></span>
				<span><a href="../member/logout.do">로그아웃</a></span>
			</c:if>
		</div>
		
		<br>
		
		<div align="center" class="header" id="logo">
			<a href="../main/main.do"><img height="130" src="../images/logo3.png"></a>
		</div>

		<!-- 상단 메뉴 시작 -->
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
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> HOME <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">회사소개</a></li>
							<li><a href="#">FamilySite</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 분양 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">분양절차</a></li>
							<li><a href="${pageContext.request.contextPath}/data/dataList.do">펫 정보</a></li>
							<li><a href="${pageContext.request.contextPath}/breed/breedList.do">분양</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 커뮤니티 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/notice/noticeList.do">공지사항</a></li>
							<li><a href="${pageContext.request.contextPath}/faq/faqList.do">FAQ</a></li>
							<li><a href="${pageContext.request.contextPath}/review/reviewList.do">분양후기</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 지점안내 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">지점안내</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
