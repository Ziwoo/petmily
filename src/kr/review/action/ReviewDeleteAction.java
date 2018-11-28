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
		
		//�α����� ���̵�� �� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(board.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ���� �� �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		//�� ����
		dao.deleteReview(num);
		
		//���ϻ���
		if(board.getRv_pet_picture()!=null){
			FileUtil.removeFile(request, board.getRv_pet_picture());
		}
		return "redirect:/board/list.do";
	}

}
