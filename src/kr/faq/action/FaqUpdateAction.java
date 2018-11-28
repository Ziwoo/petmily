package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.FileUtil;

public class FaqUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("faq_num"));
		FaqDao dao = FaqDao.getInstance();
		
		//수정 전 데이터
		Faq dbBoard = dao.getFaq(num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(dbBoard.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//전송된 정보 저장
		Faq faq = new Faq();
		faq.setFaq_num(num);
		faq.setFaq_subject(request.getParameter("faq_subject"));
		faq.setFaq_content(request.getParameter("faq_content"));
		faq.setFaq_passwd(request.getParameter("faq_passwd"));
		
		dao.updateFaq(faq);
		
		return "redirect:/board/list.do";
	}

}
