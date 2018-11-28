<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>회원제 댓글 게시판</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/jquery.slides.min.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
ul, li {
	list-style:none;
	text-align:center;
}
#slides {
	display:none
}
#slides .slidesjs-navigation {
	margin-top: 5px;
}
a.slidesjs-next, a.slidesjs-previous, a.slidesjs-play, a.slidesjs-stop {
	background-image: url(../images/btns-next-prev.png);
	background-repeat: no-repeat;
	display: block;
	width: 12px;
	height: 18px;
	overflow: hidden;
	text-indent: -9999px;
	float: left;
	margin-right: 5px;
}
a.slidesjs-next {
	margin-right: 10px;
	background-position: -12px 0;
}
a:hover.slidesjs-next {
	background-position: -12px -18px;
}
a.slidesjs-previous {
	background-position: 0 0;
}
a:hover.slidesjs-previous {
	background-position: 0 -18px;
}
a.slidesjs-play {
	width: 15px;
	background-position: -25px 0;
}
a:hover.slidesjs-play {
	background-position: -25px -18px;
}
a.slidesjs-stop {
	width: 18px;
	background-position: -41px 0;
}
a:hover.slidesjs-stop {
	background-position: -41px -18px;
}
.slidesjs-pagination {
	margin: 7px 0 0;
	float: right;
	list-style: none;
}
.slidesjs-pagination li {
	float: left;
	margin: 0 1px;
}
.slidesjs-pagination li a {
	display: block;
	width: 13px;
	height: 0;
	padding-top: 13px;
	background-image: url(../images/pagination.png);
	background-position: 0 0;
	float: left;
	overflow: hidden;
}
.slidesjs-pagination li a.active, .slidesjs-pagination li a:hover.active
	{
	background-position: 0 -13px
}
.slidesjs-pagination li a:hover {
	background-position: 0 -26px
}
.navbar {
	overflow: hidden
}
</style>
<style>
#slides {
	display: none
}
.container {
	margin: 0 auto
}
/* For tablets & smart phones */
@media ( max-width : 767px) {
	body {
		padding-left: 20px;
		padding-right: 20px;
	}
	.container {
		width: auto
	}
}
/* For smartphones */
@media ( max-width : 480px) {
	.container {
		width: auto
	}
}
/* For smaller displays like laptops */
@media ( min-width : 768px) and (max-width: 979px) {
	.container {
		width: 724px
	}
}
/* For larger displays */
@media ( min-width : 1000px) {
	.container {
		width: 1000px
	}
}
</style>
</head>
<body>


	<div class="container">
		<table id="login" align="right">
			
			<c:if test="${empty user_id}">
			<tr>
				<td>
					<h5><a href="../member/registerUserForm.do">회원가입&nbsp;&nbsp;</a></h5>
				</td>
				<td>
					<h5><a href="../member/loginForm.do">로그인</a></h5>
				</td>
			</tr>
			</c:if>
			
			
			<c:if test="${!empty user_id}">
			<tr>
				<td>
					<h5><a href="../member/modifyUserForm.do">회원정보수정&nbsp;&nbsp;</a></h5>
				</td>
				<td>
					<h5><a href="../member/deleteUserForm.do">회원탈퇴 </a></h5>
				</td>
				[${user_id}님 로그인 중]
				<td>
					<h5><a href="../member/logout.do">로그아웃</a></h5>
				</td>
			</tr>
			</c:if>
			
			
		</table>
		<br>
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
		
		<!-- 사진 슬라이드-->
		<br><br>
		<div id="wrap">
			<div class="container">
				<div id="slides">
					<img src="../images/img1.png" alt=""> 
					<img src="../images/img2.png" alt=""> 
					<img src="../images/img3.png" alt=""> 
					<img src="../images/img4.png" alt="">
					<img src="../images/img5.png" alt="">
					<img src="../images/img6.png" alt="">
					<img src="../images/img7.png" alt="">
					<img src="../images/img8.png" alt="">
				</div>
			</div>
		</div>
		
		<script>
		/* 사진 슬라이드 스크립트 */
		$(function() {
		      $('#slides').slidesjs({
		        width: 640,
		        height: 360,
		        play: {
		          active: true,
		          auto: true,
		          interval: 4000,
		          swap: true
		        }
		      });
		 });
		</script>
		<br><br><br>
		
		<!-- 게시판(공지사항,FAQ 등) 미리보기 -->
		<div class="row">
			<div class="col-sm-6 col-md-6">
				<a href="#" class="thumbnail"><img src="../images/NOTICE.jpg"></a>
			</div>
			<div class="col-sm-6 col-md-6">
				<a href="#" class="thumbnail"><img src="../images/FAQ.jpg"></a>
			</div>
		</div>
		<br><br>
		
		<!-- 패밀리사이트 -->
		<div class="row">
			<div class="col-sm-6 col-md-3">
				<a href="#" class="thumbnail"><img src="../images/family1.PNG"></a>
			</div>
			<div class="col-sm-6 col-md-3">
				<a href="#" class="thumbnail"><img src="../images/family2.PNG"></a>
			</div>
			<div class="col-sm-6 col-md-3">
				<a href="#" class="thumbnail"><img src="../images/family3.PNG"></a>
			</div>		
			<div class="col-sm-6 col-md-3">
				<a href="#" class="thumbnail"><img src="../images/family4.PNG"></a>
			</div>			
		</div>
		<br><br>
		
		<h3>펫 정보</h3>
	<c:if test="${count > 0 }">
			<table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회</th>
				</tr>
				<c:forEach var="data" items="${list}" >
				<tr>
					<td>${data.pd_num}</td>
				
					<td><a href="dataDetail.do?pd_num=${data.pd_num}">${data.pd_title}</a></td>
					<td>${data.mem_id}</td>
					<td>${data.pd_regdate}</td>
					<%-- <td>${board.pd_hit}</td> --%>
				</tr>
				</c:forEach>
			</table>			
		</c:if>
		<c:if test="${count == 0 }">
			등록된 게시물이 없습니다.			
		</c:if>
		<c:if test="${count > 0 }">
			<div class="align-center">
				${pagingHtml}
			</div>
		</c:if>
	
	
		<!-- 푸터 시작 -->
		
		
		<div id="footer">
			<p align="center">© Company 2017 Petmily</p>
		</div>

	</div>
	<!-- /container -->
</body>
</html>