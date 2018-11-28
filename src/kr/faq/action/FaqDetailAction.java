package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.StringUtil;

public class FaqDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 번호 반환
				int num = Integer.parseInt(request.getParameter("faq_num"));
				FaqDao dao = FaqDao.getInstance();
				
				//조회수 증가
				dao.updateReadcount(num);
				
				//글 번호와 매칭되는 레코드(데이터) 반환
				Faq faq = dao.getFaq(num);
				
				//제목은 HTML 불허
				faq.setFaq_subject(StringUtil.useNoHtml(faq.getFaq_subject()));
				//내용은 HTML 불허 줄바꿈 처리
				faq.setFaq_content(StringUtil.useBrNoHtml(faq.getFaq_content()));
				
				request.setAttribute("faq", faq);
				
				return "/views/board/detail.jsp";
	}

}
