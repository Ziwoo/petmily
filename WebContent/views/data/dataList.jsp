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
<title>펫정보 - 많은 펫들을 구경하세요!</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/data.js"></script><!-- 유효성검사가 필요할경우 -->
<script src="../js/jq.uery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="../css/datastyle.css">
<link rel="stylesheet" type="text/css" href="../css/boardstyle.css">
<style type="text/css">
img{
	width:200px;
	
}

</style>
<head>
<meta charset="UTF-8">
</head>
<body>
<div class="container">
	
	<jsp:include page="/views/template/top.jsp"></jsp:include>

	<div class="page-main-style">
		<h2><img alt="이미지" src="../images/icon1.png">펫 정보 게시판</h2>
		<div class="row">
		<c:if test="${count > 0 }">

		<table>
		<tr>
		<td>
		<c:forEach var="data" items="${list}" >
		<div class="col-sm-6 col-md-3">
		
	     <div class="thumbnail">
		
		<c:if test="${!empty data.pd_pic}" >
		
            <a  class="align-center" href="dataDetail.do?pd_num=${data.pd_num}" >
               <img src="../upload/${data.pd_pic}" class="detail-img" style="width:100%; height:200px;">
              </a>
            </c:if>
             <div  align="center" >
		   <div class="caption">
		 			<h3><b>${data.pd_title}</b></h3>
		 			<p>${data.pd_content}</p>
		 			
		 			<a href="dataDetail.do?pd_num=${data.pd_num}"
		 			 class="btn btn-default">상세글 보기</a>
		 		</div>
               
            
		 	</div>
		 	</div>
		 	</div>
			</c:forEach>
			</td>
			</tr>
					 	</table>   	
			
			
			<div align="center">
			<form  id="search_form" action="dataList.do" method="get">
			<a  class="searchWrap">
				
					<select name="keyfield">
						<option value="pd_title">제목</option>
						<option value="mem_id">ID</option>
						<option value="pd_content">내용</option>
					</select>
				
					<input type="search" size="16" name="keyword" id="keyword">
				
					<input class="btnGrey" type="submit" value="찾기">
				
			</a>
		</form>
		</div>
		
				<div align="right" class="list-space align-right">
		<c:if test="${empty user_id}"> </c:if>
			<div class="btnWrap">
			<input class="btnBlack" type="button" value="글쓰기" 
				onclick="location.href='dataWriteForm.do'"
				>
			
			<input class="btnBlack" type="button" value="목록" onclick="location.href='dataList.do'">
			
		</div>
		</div>
		</c:if>
		</div>
		
		
		
		<c:if test="${count == 0 }">
			등록된 게시물이 없습니다.			
		</c:if>
		<c:if test="${count > 0 }">
			<div align="center" class="align-center">
					<br><br>
			
				${pagingHtml}
			</div>
		</c:if>
	</div>
	</div>
	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>

	 <%-- <table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					
				</tr>
				<c:forEach var="data" items="${list}" >
				<tr>
					<td>${data.pd_num}</td>
					<td><a href="dataDetail.do?pd_num=${data.pd_num}">${data.pd_title}</a></td>
					<td>${data.mem_id}</td>
					<td>${data.pd_regdate}</td>
					<td><c:if test="${!empty data.pd_pic }">
					<img src="../upload/${data.pd_pic}" class="list-img">
					</c:if></td>
					<td>${board.pd_hit}</td>
				</tr>
				
				</c:forEach>
			</table>		 --%>	
			
			<%-- <a><img src="dataDetail.do?pd_pic=${data.pd_pic}">${data.pd_pic}</a>
			<div class="caption">
			<h3><a href="dataDetail.do?pd_num=${data.pd_num}">${data.pd_title}</a></h3>
			<p><a href="dataDetail.do?pd_num=${data.pd_num}">${data.pd_content}</a></p> --%>
			<!-- <a href="#" class="btn btn-primary">Button</a>
		 	<a href="#" class="btn btn-default">Button</a> -->
			
</body>
</html>