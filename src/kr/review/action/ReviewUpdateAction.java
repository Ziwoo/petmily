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
		//수정전 데이터
		Review dbBoard = dao.getReview(rv_num);
		
		//로그인한 아이디와 글작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(dbBoard.getMem_id())){
			if(rv_pet_picture!=null){
				FileUtil.removeFile(request, rv_pet_picture);
			}
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다");
			return "/views/common/notice.jsp";
		}
		//전송된 정보 저장
		Review review = new Review();
		review.setRv_num(rv_num);
		review.setRv_subject(multi.getParameter("rv_subject"));
		review.setRv_content(multi.getParameter("rv_content"));
		review.setIp(request.getRemoteAddr());
		
		if(rv_pet_picture!=null){//새 이미지로 교체
			review.setRv_pet_picture(FileUtil.rename(request, rv_pet_picture));
		}else{//전송된 이미지가 없을 경우
			review.setRv_pet_picture(dbBoard.getRv_pet_picture());
		}
		
		dao.updateReview(review);
		
		if(rv_pet_picture!=null){//새 이미지로 교체할 때
			               //원래 이미지 제거
			FileUtil.removeFile(request, dbBoard.getRv_pet_picture());
		}
		
		
		return "redirect:/board/list.do";
	}

}
