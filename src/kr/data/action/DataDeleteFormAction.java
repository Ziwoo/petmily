package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class DataDeleteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//�α��� ���� üũ
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//���޹��� br_num�� breedDelete.jsp�� ����
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		request.setAttribute("pd_num", pd_num);
		
		String pd_passwd = request.getParameter("pd_passwd");
		request.setAttribute("pd_passwd", pd_passwd);
		
		return "/views/data/dataDeleteForm.jsp";
	}

}
