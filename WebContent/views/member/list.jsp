<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
	<div class="page-main-style">
	<jsp:include page="/views/template/top.jsp"></jsp:include>
		<h2>회원 목록</h2>
		<form id="search_form" action="userList.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="mem_id">ID</option>
						<option value="mem_name">이름</option>
						<option value="mem_email">이메일</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword">
				</li>
				<li>
					<input type="submit" value="찾기">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<input type="button" value="홈으로" onclick="location.href='../main/main.do'">
		</div>
		<c:if test="${count > 0 }">
			<table>
				<tr>
					
					<th>회원id</th>
					<th>회원이름</th>
					<th>회원휴대번호</th>
					<th>회원이메일</th>
					<th>회원가입날짜</th>
					<th>회원탈퇴</th>
					
				</tr>
				<c:forEach var="p_member" items="${list}">
				<tr>
					
					<td><a href="modifyUserFormByAdmin.do?mem_id=${p_member.mem_id}">${p_member.mem_id}</a></td>
					<td>${p_member.mem_name}</td>
					<td>${p_member.mem_cell}</td>
					<td>${p_member.mem_email}</td>
					<td>${p_member.mem_register}</td>
					<td><a href="deleteUserFormByAdmin.do?mem_id=${p_member.mem_id}">회원탈퇴</button></td>
					
				</tr>
				</c:forEach>
			</table>			
		</c:if>
		<c:if test="${count == 0 }">
			등록된 회원이 없습니다.			
		</c:if>
		<c:if test="${count > 0 }">
			<div class="align-center">
				${pagingHtml}
			</div>
		</c:if>
		<jsp:include page="/views/template/bottom.jsp"></jsp:include>
	</div>
	
</body>
</html>