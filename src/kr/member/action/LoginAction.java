package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		
		MemberDao dao = MemberDao.getInstance();
		Member p_member = dao.getMember(user_id);
		boolean check = false;
		
		if(p_member!=null){
			//비밀번호 체크
			check = p_member.isCheckedPasswd(mem_passwd);
		}
		
		if(check){//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
		}
		request.setAttribute("check", check);
		
		return "/views/member/login.jsp";
	}
}







