package kr.breed.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.PagingUtil;

public class BreedListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//검색
		String keyfield = request.getParameter("keyfield");
		String keyword =  request.getParameter("keyword");
		
		if(keyfield==null){keyfield="";}
		if(keyword==null){keyword="";}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){pageNum="1";}
		
		int rowCount = 20;
		int pageCount = 10;
		int currentPage = Integer.parseInt(pageNum);
		
		BreedDao dao = BreedDao.getInstance();
		int count = dao.getBreedCount(keyfield, keyword);
		
		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,
										count,rowCount,pageCount,"breedList.do");
		
		List<Breed> list = null;
		if(count>0){
			list = dao.getListBreed(page.getStartCount(), 
									page.getEndCount(),
									keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/views/breed/breedList.jsp";
	}

}
