package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class BreedDeleteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//전달받은 br_num을 breedDelete.jsp에 전달
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		request.setAttribute("br_num", br_num);
		
		String br_passwd = request.getParameter("br_passwd");
		request.setAttribute("br_passwd", br_passwd);
		
		return "/views/breed/breedDeleteForm.jsp";
	}

}
