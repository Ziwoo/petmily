$(document).ready(function(){
	//글등록, 수정 유효성 체크
	$('#write_form,#update_form').submit(function(){
		if($('#faq_subject').val()==''){
			alert('제목을 입력하세요!');
			$('#faq_subject').focus();
			return false;
		}
		if($('#faq_content').val()==''){
			alert('내용을 입력하세요!');
			$('#faq_content').focus();
			return false;
		}
	});
});