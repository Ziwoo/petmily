<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/notice.js"></script>
</head>
<body>
	<div class="page-main-style">
		<h2>공지사항</h2>
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="nb_subject">제목</option>
						<option value="mem_id">ID</option>
						<option value="nb_content">내용</option>
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
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
				<c:if test="${empty user_id}">disabled="disabled"</c:if>>
			<input type="button" value="홈으로" onclick="location.href='../main/main.do'">
		</div>
		<c:if test="${count > 0 }">
			<table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회</th>
				</tr>
				<c:forEach var="notice" items="${list}">
				<tr>
					<td>${notice.nb_num}</td>
					<td><a href="detail.do?nb_num=${notice.nb_num}">${notice.nb_subject}</a>
					<td>${notice.mem_id}</td>
					<td>${notice.nb_reg_date}</td>
					<td>${notice.nb_readcount}</td>
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
	</div>
</body>
</html>