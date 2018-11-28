package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.StringUtil;

public class BreedDetailAction implements Action{
  
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//글 번호 반환
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		
		BreedDao dao = BreedDao.getInstance();
		
		//조회수 증가
		dao.updateReadCount(br_num);
		
		//글 번호와 매칭되는 레코드(데이터) 반환
		Breed breed = dao.getBreed(br_num);
		
		//제목은 HTML불허
		breed.setBr_subject(StringUtil.useNoHtml(breed.getBr_subject()));
		//내용은 HTML 불허, 줄바꿈 처리
		breed.setBr_content(StringUtil.useBrNoHtml(breed.getBr_content()));
		
		request.setAttribute("breed", breed);
		
		return "/views/breed/breedDetail.jsp";
	}

}
