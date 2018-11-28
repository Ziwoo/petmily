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
		
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//���۵� �����Ϳ� ���� ���ڵ�
		request.setCharacterEncoding("utf-8");
		//�ڹٺ� ����
		Member p_member = new Member();
		//�ڹٺ� ���۵� ������ ����
		//id�� ���۵��� �ʾұ� ������ session�� ����� user_id.
		//���۵� ������ : name,passwd,phone,email,zipcode,
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
		//MemberDao�� updateMember�� �ڹٺ� ����
		MemberDao dao = MemberDao.getInstance();
		dao.updateMember(p_member);
		return "/views/member/modifyUser.jsp";
	}

}