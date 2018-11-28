package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.review.dao.ReviewDao;
import kr.review.domain.Review;
import kr.util.FileUtil;

public class ReviewDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int num = Integer.parseInt(request.getParameter("rv_num"));
		ReviewDao dao = ReviewDao.getInstantce();
		Review board = dao.getReview(num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(board.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 삭제 할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//글 삭제
		dao.deleteReview(num);
		
		//파일삭제
		if(board.getRv_pet_picture()!=null){
			FileUtil.removeFile(request, board.getRv_pet_picture());
		}
		return "redirect:/board/list.do";
	}

}
