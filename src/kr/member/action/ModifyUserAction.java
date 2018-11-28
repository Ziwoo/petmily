package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//전송된 데이터에 대한 인코딩
		request.setCharacterEncoding("utf-8");
		//자바빈 생성
		Member p_member = new Member();
		//자바빈에 전송된 데이터 저장
		//id는 전송되지 않았기 때문에 session에 저장된 user_id.
		//전송된 데이터 : name,passwd,phone,email,zipcode,
		//               address1,address2
		p_member.setMem_id(user_id);
		p_member.setMem_name(request.getParameter("mem_name"));
		p_member.setMem_passwd(request.getParameter("mem_passwd"));
		p_member.setMem_cell(request.getParameter("mem_cell"));
		p_member.setMem_email(request.getParameter("mem_email"));
		p_member.setMem_zipcode1(request.getParameter("mem_zipcode1"));
		p_member.setMem_zipcode2(request.getParameter("mem_zipcode2"));
		p_member.setMem_addr1(request.getParameter("mem_addr1"));
		p_member.setMem_addr2(request.getParameter("mem_addr2"));
		//MemberDao의 updateMember에 자바빈 전달
		MemberDao dao = MemberDao.getInstance();
		dao.updateMember(p_member);
		return "/views/member/modifyUser.jsp";
	}

}