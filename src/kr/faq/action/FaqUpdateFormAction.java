package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;

public class FaqUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		//�α����� �Ǿ� ���� ������ �α����Ϸ� ������.
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int num = Integer.parseInt(request.getParameter("faq_num"));
		FaqDao dao = FaqDao.getInstance();
		Faq faq = dao.getFaq(num);
		
		//(�α����� ��)Ÿ���� ���� �����ϴ� ���� ����
		if(user_id!=null && !user_id.equals(faq.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("faq", faq);
		
		return "/views/board/updateForm.jsp";
	}

}
