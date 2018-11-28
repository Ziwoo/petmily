$(document).ready(function(){
	//글 등록, 수정 유효성 체크
	$('#write_form, #update_form').submit(function(){
		//제목
		if($('#br_subject').val()==''){
			alert('제목을 입력하세요.');
			$('#br_subject').focus();
			return false;
		}
		//내용
		if($('#br_content').val()==''){
			alert('내용을 입력하세요.');
			$('#br_content').focus();
			return false;
		}	
		//비밀번호
		if($('#br_passwd').val()==''){
			alert('비밀번호를 입력하세요.');
			$('#br_passwd').focus();
			return false;
		}
		//파일
		if($('#br_pic').val()==''){
			alert('사진을 업로드하세요.');
			$('#br_pic').focus();
			return false;
		}
		
		
	});
	
	$('#delete_form').submit(function(){
		
		//비밀번호
		if($('#br_passwd').val()==''){
			alert('비밀번호를 입력하세요.');
			$('#br_passwd').focus();
			return false;
		}
		
		//비밀번호확인 체크
		if($('#check_passwd').val()==''){
			alert('비밀번호확인을 입력하세요.');
			$('#check_passwd').focus();
			return false;
		}
		
		//비밀번호와 비밀번호확인 일치 여부 체크
		if($('#br_passwd').val() != $('#check_passwd').val()){
			alert('비밀번호와 비밀번호확인이 일치하지 않습니다.');
			$('#check_passwd').val('').focus();
			return false;
		}
		
	});
});