$(document).ready(function(){
	//글등록, 수정 유효성 체크
	$('#write_form,#update_form').submit(function(){
		if($('#nb_subject').val()==''){
			alert('제목을 입력하세요!');
			$('#nb_subject').focus();
			return false;
		}
		if($('#nb_content').val()==''){
			alert('내용을 입력하세요!');
			$('#nb_content').focus();
			return false;
		}
	});
});