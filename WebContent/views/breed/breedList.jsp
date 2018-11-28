<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분양목록</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/breed.js"></script>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>

	<div class="page-main-style">
	<h2>분양목록</h2>
	<form id="search_form" action="breedList.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="br_subject">제목</option>
					<option value="mem_id">ID</option>
					<option value="br_content">내용</option>
				</select>
			</li>
			<li>
				<input type="search" size="16" name="keyword"
						id="keyword" >
			</li>
			<li>
				<input type="submit" value="찾기" >
			</li>
		</ul>
	</form>
	
	<div class="list-space align-right" >
		<input type="button" value="글쓰기"
			onclick="location.href='breedWriteForm.do'"
			<c:if test="${empty user_id}"> disabled="disabled"</c:if>
			>
		<input type="button" value="목록"
			onclick="location.href='breedList.do'">	
	</div>
	<c:if test="${count>0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회</th>
			</tr>
			<c:forEach var="breed" items="${list}">
			<tr>
				<td>${breed.br_num}</td>
				<td><a href="breedDetail.do?br_num=${breed.br_num}">${breed.br_subject}</a></td>
				<td>${breed.mem_id}</td>
				<td>${breed.br_regdate}</td>
				<td>${breed.br_readcount}</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${count==0}">
		등록된 게시물이 없습니다.
	</c:if>
	<c:if test="${count>0}">
	<div class="align-center">
		${pagingHtml}
	</div>
	</c:if>
	</div>

	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>