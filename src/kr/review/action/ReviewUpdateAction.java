package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.review.dao.ReviewDao;
import kr.review.domain.Review;
import kr.util.FileUtil;

public class ReviewUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int rv_num = Integer.parseInt(multi.getParameter("rv_num"));
		String rv_pet_picture = multi.getFilesystemName("rv_pet_picture");
		
		ReviewDao dao = ReviewDao.getInstantce();
		//������ ������
		Review dbBoard = dao.getReview(rv_num);
		
		//�α����� ���̵�� ���ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(dbBoard.getMem_id())){
			if(rv_pet_picture!=null){
				FileUtil.removeFile(request, rv_pet_picture);
			}
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�");
			return "/views/common/notice.jsp";
		}
		//���۵� ���� ����
		Review review = new Review();
		review.setRv_num(rv_num);
		review.setRv_subject(multi.getParameter("rv_subject"));
		review.setRv_content(multi.getParameter("rv_content"));
		review.setIp(request.getRemoteAddr());
		
		if(rv_pet_picture!=null){//�� �̹����� ��ü
			review.setRv_pet_picture(FileUtil.rename(request, rv_pet_picture));
		}else{//���۵� �̹����� ���� ���
			review.setRv_pet_picture(dbBoard.getRv_pet_picture());
		}
		
		dao.updateReview(review);
		
		if(rv_pet_picture!=null){//�� �̹����� ��ü�� ��
			               //���� �̹��� ����
			FileUtil.removeFile(request, dbBoard.getRv_pet_picture());
		}
		
		
		return "redirect:/board/list.do";
	}

}
