package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;
import kr.member.domain.MemberAjax;

public class CheckDuplicatedIdAjaxAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberAjax memberAjax = new MemberAjax(); 
		try{
			String user_id = request.getParameter("mem_id");
			MemberDao dao = MemberDao.getInstance();
			Member p_member = dao.getMember(user_id);
			
			if(p_member == null){//아이디 미중복
				memberAjax.setResult("idNotFound");
			}else{//아이디 중복
				memberAjax.setResult("idDuplicated");
			}
		}catch(Exception e){
			memberAjax.setResult("failure");
			e.printStackTrace();
		}
		
		/*
		 * JSON형식으로 변환하기를 원하는 문자열을 MemberAjax의
		 * 프로퍼티에 저장한 후 MemberAjax를 ObjectMapper의
		 * writeValueAsString에 전달해서 일반 문자열 데이터를
		 * JSON형식의 문자열 데이터로 변환
		 */
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(memberAjax);
		request.setAttribute("jsonData", jsonData);
		
		return "/views/common/ajaxView.jsp";
	}

}







