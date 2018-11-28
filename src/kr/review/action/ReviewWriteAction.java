package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.review.dao.ReviewDao;
import kr.review.domain.Review;
import kr.util.FileUtil;

public class ReviewWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		Review review = new Review();
		review.setRv_subject(multi.getParameter("rv_subject"));
		review.setRv_content(multi.getParameter("rv_content"));
		review.setIp(request.getRemoteAddr());
		//파일명 변경
		review.setRv_pet_picture(FileUtil.rename(request,multi.getFilesystemName("rv_pet_picture")));
		review.setMem_id(user_id);
		
		
		ReviewDao dao = ReviewDao.getInstantce();
		dao.insertReview(review);
		
		return "/views/board/write.jsp";
	}

}