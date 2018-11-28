$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	//댓글 목록
	function selectData(pageNum,num){
		currentPage = pageNum;

		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('#output').empty();
		}
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,num:num},
			url:'listReplyAjax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				}else{
					$(list).each(function(index,item){
						var output = '';
						output += '<div class="item" id="i'+item.re_num+'">';
						output += '<h4>' + item.id + '</h4>';
						output += '<div class="sub-item">';
						output += '<p>' + item.re_content + '</p>';
						output += '<div>' + item.re_date;
						output += '<hr size="1" noshade>';
						output += '</div>';
						output += '</div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging_button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging_button').show();
					}
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('목록 호출시 네트워크 오류!');
				
			}
		})
	}
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging_button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum,$('#num').val());
	});
	
	//댓글 등록
	$('#re_form').submit(function(event){
		if($('#re_content').val()==''){
			alert('내용을 입력하세요');
			$('#re_content').focus();
			return false;
		}
		var data = $(this).serialize();
		
		//등록
		$.ajax({
			type:'post',
			data:data,
			url:'writeReplyAjax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					//폼초기화
					initForm();
					//수정폼 초기화
					initModifyForm();
					/*
					 * 댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫번째 페이지의 게시글을 다시 호출함
					 */
					selectData(1,$('#num').val());
				}else{
					alert('등록시 오류 발생');
				}
			},
			error:function(){
				alert('댓글 작성 오류!');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').html('300/300');
	}
	//textarea에 내용 입력시 글자수 체크
	$('#re_content, #mre_content').keyup(function(){});
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		
	}
	
	//초기 데이터(목록) 호출
	selectData(1,$('#num').val());
});