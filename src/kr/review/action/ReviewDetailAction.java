package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.review.dao.ReviewDao;
import kr.review.domain.Review;
import kr.util.StringUtil;

public class ReviewDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 번호 반환
		int num = Integer.parseInt(request.getParameter("rv_num"));
		ReviewDao dao = ReviewDao.getInstantce();
		
		//조회수 증가
		dao.updateReadcount(num);
		
		//글 번호와 매칭되는 레코드(데이터) 반환
		Review board = dao.getReview(num);
		
		//제목은 HTML 불허
		board.setRv_subject(StringUtil.useNoHtml(board.getRv_subject()));
		//내용은 HTML 불허 줄바꿈 처리
		board.setRv_content(StringUtil.useBrHtml(board.getRv_content()));
		
		request.setAttribute("board", board);
		
		return "/views/board/detail.jsp";
	}

}
