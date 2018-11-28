$(document).ready(function(){
	//아이디 중복 체크 여부
	var checkIdDuplicated = 0;
	
	//아이디 중복 체크
	$('#id_check').click(function(){
		if($('#mem_id').val()==''){
			alert('아이디를 입력하세요!');
			$('#mem_id').focus();
			return;
		}
		$('#message_id').html('');//메시지 초기화
		$('#loading').show();//로딩 이미지 노출
		
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();//로딩 이미지 감추기
				
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','#000').text('등록 가능 ID');
					checkIdDuplicated = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('중복된 ID');
					$('#mem_id').val('').focus();
					checkIdDuplicated = 0;
				}else{
					alert('아이디 중복 체크 오류 발생')
				}
			},
			error:function(){
				$('#loading').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #mem_id').keyup(function(){
		checkIdDuplicated = 0;
		$('#message_id').text('');
	})
	
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#mem_id').val()==''){
			alert('아이디를 입력하세요!');
			$('#mem_id').focus();
			return false;
		}
		if(checkIdDuplicated==0){
			alert('아이디 중복 체크 필수!');
			$('#id_check').focus();
			return false;
		}
		if($('#mem_name').val()==''){
			alert('이름을 입력하세요!');
			$('#mem_name').focus();
			return false;
		}
		if($('#mem_passwd').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#mem_passwd').focus();
			return false;
		}
		if($('#mem_cell').val()==''){
			alert('전화번호를 입력하세요!');
			$('#mem_cell').focus();
			return false;
		}
		if($('#mem_email').val()==''){
			alert('이메일을 입력하세요!');
			$('#mem_email').focus();
			return false;
		}
		/*if($('#mem_zipcode').val()==''){
			alert('우편번호를 입력하세요!');
			$('#mem_zipcode').focus();
			return false;
		}
		if($('#mem_addr1').val()==''){
			alert('주소를 입력하세요!');
			$('#mem_addr1').focus();
			return false;
		}
		if($('#mem_addr2').val()==''){
			alert('나머지주소를 입력하세요!');
			$('#mem_addr2').focus();
			return false;
		}*/
	});
	
	//로그인 유효성 체크
	$('#login_form').submit(function(){
		if($('#mem_id').val()==''){
			alert('아이디를 입력하세요');
			$('#mem_id').focus();
			return false;
		}
		if($('#mem_passwd').val()==''){
			alert('비밀번호를 입력하세요');
			$('#mem_passwd').focus();
			return false;
		}
	});
	
	//회원정보수정 유효성 체크 (기본적으로 값을 불러오지만 <비밀번호>는 입력필 )
	$('#modify_form').submit(function(){
		if($('#mem_passwd').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#mem_passwd').focus();
			return false;
		}
	});	
	
	//회원탈퇴 유효성 체크
	$('#delete_form').submit(function(){
		if($('#mem_passwd').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#mem_passwd').focus();
			return false;
		}
		if($('#check_passwd').val()==''){
			alert('비밀번호 확인을 입력하세요!');
			$('#check_passwd').focus();
			return false;
		}

		if($('#mem_passwd').val()!=$('#check_passwd').val()){
			alert('비밀번호와 비밀번호 확인이 불일치합니다.');
			$('#check_passwd').val('').focus();
			return false;
		}		
	});
});