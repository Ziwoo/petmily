<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글상세</title>
<!-- <link rel="stylesheet" type="text/css" href="../css/style.css">기본스타일 적용
 -->
<link rel="stylesheet" type="text/css" href="../css/boardstyle.css"><!-- 기본스타일 적용 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/data.js"></script><!-- 유효성검사가 필요할경우 -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<jsp:include page="/views/template/top.jsp"></jsp:include>

	<%--  <div class="page-main-style">
		<h2>게시판 글상세</h2>
		<ul>
			<li>글번호 : ${data.pd_num}</li>
			<li>제목 : ${data.pd_title}</li>
			<li>작성자 : ${data.mem_id}</li>
			 <li>파일이름 : ${data.pd_pic}</li> 
		</ul>
		
		
		<hr size="1" noshade width="100%">
		<div class="align-center">
			<c:if test="${!empty data.pd_pic }">
				<img src="${pageContext.request.contextPath}/upload/${data.pd_pic}" class="detail-img">
			</c:if>
		</div>
		<p>
			${data.pd_content}
		</p> --%>
		
		
		<h2>게시판 글상세</h2>
		
		<div class="align-right-bottommr">
			
			<input class="btnBlack" type="button" value="수정" onclick="location.href='dataUpdateForm.do?pd_num=${data.pd_num}'"
				<c:if test="${user_id != data.mem_id}"> disabled="disabled"</c:if>
			><input class="btnBlack" type="button" value="삭제" onclick="location.href='dataDeleteForm.do?pd_num=${data.pd_num}'"
				<c:if test="${user_id != data.mem_id}"> disabled="disabled"</c:if>
			><input class="btnBlack" type="button" value="목록" onclick="location.href='dataList.do'">
		</div> 
		
		<div class="commBoard-detail">
		<table>			
			<colgroup>
             <col width="10%">
             <col width="50%">
             <col width="10%">
             <col width="10%">
             <col width="10%">
             <col width="10%">
			</colgroup>
			
			<tbody>
				<tr>
					<th>제목</th>
					<td colspan="10">${data.pd_title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${data.mem_id}</td>
					
					<th>등록일</th>
					<td>${data.pd_regdate}</td>
					
				</tr>
			</tbody>
		</table>
		
		<div class="align-center-margin">
			${notice.nb_content}
			<c:if test="${!empty data.pd_pic }">
				<img src="../upload/${data.pd_pic}" class="detail-img">
			</c:if>
		</div>
		
		<p align="center">
		<br><br>
			${data.pd_content}
		</p>
		
		<hr size="1" noshade width="100%">
		</div>
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		<%-- <div id="reply_div">
			<span class="reply-title">댓글 달기</span>
			<form id="re_form">
				<input type="hidden" name="num" id="num" value="${board.num}">
				<input type="hidden" name="id" value="${user_id}" id="user_id">
				<textarea rows="3" cols="50" name="re_content" id="re_content" maxlength="300" class="rep-content"
					<c:if test="${empty user_id}">disabled="disabled"</c:if>
				><c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>
				<c:if test="${!empty user_id}">
					<div id="re_first">
						<span class="letter-count">300/300</span>
					</div>
					<div id="re_second" class="align-right">
						<input type="submit" value="전송">
					</div>
				</c:if>
			</form>
		</div> --%>
		
		<!-- 목록 출력 -->
		<%-- <div id="output"></div>
		<div class="paging_button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="../images/ajax-loader.gif">
		</div> --%>
		<!-- 수정폼 -->
		<%-- <div id="modify_div" style="display:none;">
			<form id="mre_form">
				<input type="hidden" name="re_num" id="mre_num">
				<input type="hidden" name="id" id="muser_id">
				<textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content"></textarea>
				<div id="mre_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="mre_second" class="align-right">
					<input type="submit" value="수정">
					<input type="button" value="취소" class="re-reset">
				</div>
				<hr size="1" noshade width="96%">
			</form>
		</div> --%>
		</div><!-- 페이지 메인 스타일 끝  -->
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>