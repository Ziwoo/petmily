<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--  viewport 메타태그에 user-scalable=no 를 추가하여 모바일 기기에서 확대/축소 기능을 끌 수 있다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<!-- 부트스트랩 css스타일이 필요한 경우 -->
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<!-- 유효성검사가 필요할경우 -->
<script type="text/javascript" src="../js/member.js"></script>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="../js/jquery-3.1.1.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="../js/bootstrap.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- 다음 우편번호서비스 가져오는 스크립트 -->
<script type="text/javascript">

//우편번호 등록서비스 함수
function openDaumZipAddress() {

    new daum.Postcode({
        
        oncomplete:function(data) {

            jQuery('#mem_zipcode1').val(data.postcode1);
            jQuery('#mem_zipcode2').val(data.postcode2);
            jQuery('#mem_addr1').val(data.address);
            jQuery('#mem_addr2').focus();
            console.log(data);	
        }
    }).open();
}

</script> 
</head>
<body>
<div class="container">	
	<jsp:include page="/views/template/top.jsp"></jsp:include>
	<br><br>
		
	<div class="page-main-style">
		<h2>회원가입</h2>
		<form action="registerUser.do" method="post" id="register_form">
			<ul>
				<li>
					<label for="mem_id">아이디</label> 
					<input type="text" name="mem_id"
						id="mem_id" size="8" maxlength="12">
					<input type="button"
						value="ID중복체크" id="id_check"> 
					<span id="message_id"></span>
					<img src="../images/ajax-loader.gif"
						width="16" height="16" id="loading" style="display:none;">
				</li>
				<li>
					<label for="mem_name">이름</label> 
					<input type="text"
						name="mem_name" id="mem_name" maxlength="10">
				</li>
				<li>
					<label for="mem_passwd">비밀번호</label> 
					<input type="password"
						name="mem_passwd" id="mem_passwd" maxlength="12">
				</li>
				<li>
					<label for="mem_cell">전화번호</label> 
					<input type="text"
						name="mem_cell" id="mem_cell" maxlength="15">
				</li>
				<li>
					<label for="mem_email">이메일</label> 
					<input type="email"
						name="mem_email" id="mem_email" maxlength="30">
				</li>
				
				<li>
				 <input type = "button" onClick = "openDaumZipAddress()" value = "주소 찾기" />
				 
					 
					<!-- 	<input type="text"
						name="mem_zipcode" id="mem_zipcode" maxlength="6"> -->
						<a>우편번호</a>
						<label for="mem_zipcode1">
						<input name="mem_zipcode1" id = "mem_zipcode1" type = "text" value = "" style="width:50px;" readonly/></label> -
    					<label for="mem_zipcode2">
    					<input name="mem_zipcode2" id = "mem_zipcode2" type = "text" value = "" style="width:50px;" readonly/>&nbsp;
    					</label>
				</li>
				<li>
					<label for="mem_addr1">주소</label>
					 <!-- <input type="text"
						name="mem_addr1" id="mem_addr1" maxlength="13"> -->
						
						    <input type="text" id = "mem_addr1" name = "mem_addr1" value = "" style = "width:240px;" readonly/>
						
						 <br/>				</li>
				<li>
					<label for="mem_addr2">나머지 주소</label> 
					    <input type="text" id = "mem_addr2" name = "mem_addr2" value = "" style = "width:200px;"/>
					
					
					<!-- <input type="text"
						name="mem_addr2" id="mem_addr2" maxlength="30"> -->
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록"> 
				<input type="button"
					value="홈으로" onclick="location.href='../main/main.do'">
			</div>
		</form>
	</div>
	
	<br><br>	
	<jsp:include page="/views/template/bottom.jsp"></jsp:include>
</div>
</body>
</html>