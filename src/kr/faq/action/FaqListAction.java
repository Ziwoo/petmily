package kr.faq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.PagingUtil;

public class FaqListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		if(keyfield == null) keyfield = "";
		if(keyword == null) keyword = "";
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		int rowCount = 20; //�� �������� �Խù� ��
		int pageCount = 10; //�� ȭ���� ������ ��
		int currentPage = Integer.parseInt(pageNum);
		
		FaqDao dao = FaqDao.getInstance();
		int count = dao.getFaqCount(keyfield, keyword);
		
		//����¡ ó��
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"list.do");
		List<Faq> list = null;
		if(count >0){
			list = dao.getListFaq(page.getStartCount(), page.getEndCount(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml",page.getPagingHtml());
		
		return "/views/board/list.jsp";
	}

}