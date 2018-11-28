package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.Member;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//���۵� �����Ϳ� ���� ���ڵ� ó��
		request.setCharacterEncoding("UTF-8");
		
		//�ڹٺ� ��ü ����
		Member p_member = new Member();
		
		//���۵� �����͸� �ڹٺ� ����
		p_member.setMem_id(request.getParameter("mem_id"));
		p_member.setMem_name(request.getParameter("mem_name"));
		p_member.setMem_passwd(request.getParameter("mem_passwd"));
		p_member.setMem_cell(request.getParameter("mem_cell"));
		p_member.setMem_email(request.getParameter("mem_email"));
		p_member.setMem_zipcode1(request.getParameter("mem_zipcode1"));
		p_member.setMem_zipcode2(request.getParameter("mem_zipcode2"));
		p_member.setMem_addr1(request.getParameter("mem_addr1"));
		p_member.setMem_addr2(request.getParameter("mem_addr2"));
		
		//MemberDao�� insertMember �޼��带 ȣ���ؼ�
		//�ڹٺ� ����
		MemberDao dao = MemberDao.getInstance();
		dao.insertMember(p_member);
		
		return "/views/member/registerUser.jsp";
	}

}







