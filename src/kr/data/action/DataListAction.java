package kr.data.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;
import kr.util.PagingUtil;

public class DataListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		if(keyfield == null) keyfield = "";
		if(keyword == null) keyword = "";
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		int rowCount = 20; //한 페이지의 게시물 수
		int pageCount = 10; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);
		
		DataDao dao = DataDao.getInstantce();
		int count = dao.getDataCount(keyfield, keyword);

		
		//페이징 처리
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,
										count,rowCount,pageCount,"dataList.do");

		List<Data> list = null;
		if(count >0){
			list = dao.getListData(page.getStartCount(), page.getEndCount(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml",page.getPagingHtml());	
		
		return "/views/data/dataList.jsp";
	}

}
