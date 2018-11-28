package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class FindIdAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String user_email = request.getParameter("user_email");
		String user_name = request.getParameter("user_name");
		
		MemberDao dao = MemberDao.getInstance();
		String user_id = dao.getMemberId(user_email,user_name);
		
		request.setAttribute("user_id", user_id);
		
		return "/views/member/findId.jsp";
		
	}

}
