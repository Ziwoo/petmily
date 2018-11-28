package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class BreedWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){//로그인이 안된 경우
			return "redirect:/views/member/loginForm.do";
		}
		
		//로그인이 된 경우 -> 분양글 작성
		return "/views/breed/breedWriteForm.jsp";
	}

}
