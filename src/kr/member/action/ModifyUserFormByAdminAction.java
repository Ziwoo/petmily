package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class ModifyUserFormByAdminAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String mem_id = request.getParameter("mem_id");
		
		MemberDao dao = MemberDao.getInstance();
		Member p_member = dao.getMember(mem_id);
		
		request.setAttribute("p_member", p_member);
		
		return "/views/member/modifyUserFormByAdmin.jsp";
	}
}