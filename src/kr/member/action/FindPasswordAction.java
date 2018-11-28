package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDao;

public class FindPasswordAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String user_email = request.getParameter("user_email");
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		
		MemberDao dao = MemberDao.getInstance();
		String user_password = dao.getMemberPassword(user_email,user_name,user_id);
		
		request.setAttribute("user_password", user_password);
		
		return "/views/member/findPassword.jsp";
	}

}
