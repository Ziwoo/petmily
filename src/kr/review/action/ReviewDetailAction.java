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
		//�� ��ȣ ��ȯ
		int num = Integer.parseInt(request.getParameter("rv_num"));
		ReviewDao dao = ReviewDao.getInstantce();
		
		//��ȸ�� ����
		dao.updateReadcount(num);
		
		//�� ��ȣ�� ��Ī�Ǵ� ���ڵ�(������) ��ȯ
		Review board = dao.getReview(num);
		
		//������ HTML ����
		board.setRv_subject(StringUtil.useNoHtml(board.getRv_subject()));
		//������ HTML ���� �ٹٲ� ó��
		board.setRv_content(StringUtil.useBrHtml(board.getRv_content()));
		
		request.setAttribute("board", board);
		
		return "/views/board/detail.jsp";
	}

}
