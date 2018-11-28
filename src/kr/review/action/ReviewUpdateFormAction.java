package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.review.dao.ReviewDao;
import kr.review.domain.Review;

public class ReviewUpdateFormAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int num = Integer.parseInt(request.getParameter("rv_num"));
		ReviewDao dao = ReviewDao.getInstantce();
		Review board =dao.getReview(num);
		if(user_id!=null && !user_id.equals(board.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("board", board);
		return "/views/board/updateForm.jsp";
	}
}
