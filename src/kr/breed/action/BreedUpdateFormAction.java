package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;

public class BreedUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//회원제서비스 이므로 로그인 여부
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){//로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		BreedDao dao = BreedDao.getInstance();
		Breed breed = dao.getBreed(br_num);
		if(user_id!=null && !user_id.equals(breed.getMem_id())){//타인이 수정폼에 들어온 경우
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("breed", breed);
		
		return "/views/breed/breedUpdateForm.jsp";
	}

}
