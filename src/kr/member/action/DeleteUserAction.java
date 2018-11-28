package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//MemberDao의 getMember에 user_id를 전달해서 한 건의
		//레코드를 자바빈에 담아 반환
		MemberDao dao = MemberDao.getInstance();
		Member p_member = dao.getMember(user_id);
		boolean check = false;
		
		//자바빈이 null이 아닐 경우 전송된 비빌번호를 받아서
		//비밀번호 체크
		if(p_member!=null){
			request.setCharacterEncoding("utf-8");
			String mem_passwd = request.getParameter("mem_passwd");
			//비밀번호 일치 여부 체크
			check = p_member.isCheckedPasswd(mem_passwd);
		}
		//check가 true이면 MemberDao의 deleteMember에 user_id
		//전달.로그아웃
		if(check){
			//회원정보삭제
			dao.deleteMember(user_id);
			//로그아웃
			session.invalidate();
		}
		//check를 request에 저장
		request.setAttribute("check", check);
		return "/views/member/deleteUser.jsp";
	}

}