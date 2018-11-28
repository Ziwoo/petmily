package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.FileUtil;

public class FaqWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		Faq faq = new Faq();
		faq.setFaq_subject(request.getParameter("faq_subject"));
		faq.setFaq_content(request.getParameter("faq_content"));
		faq.setFaq_passwd(request.getParameter("faq_passwd"));
		
		faq.setMem_id(user_id);
		
		FaqDao dao = FaqDao.getInstance();
		dao.insertFaq(faq);
		
		return "/views/board/write.jsp";
	}

}
