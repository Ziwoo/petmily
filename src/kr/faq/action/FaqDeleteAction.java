package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.FileUtil;

public class FaqDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int num = Integer.parseInt(request.getParameter("faq_num"));
		
		FaqDao dao = FaqDao.getInstance();
		Faq faq = dao.getFaq(num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(faq.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 삭제할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//글 삭제
		dao.deleteFaq(num);
		
		return "redirect:/board/list.do";
	}

}
