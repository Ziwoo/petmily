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
		
		//ȸ�������� �̹Ƿ� �α��� ����
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){//�α����� �ȵ� ���
			return "redirect:/member/loginForm.do";
		}
		
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		BreedDao dao = BreedDao.getInstance();
		Breed breed = dao.getBreed(br_num);
		if(user_id!=null && !user_id.equals(breed.getMem_id())){//Ÿ���� �������� ���� ���
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("breed", breed);
		
		return "/views/breed/breedUpdateForm.jsp";
	}

}
