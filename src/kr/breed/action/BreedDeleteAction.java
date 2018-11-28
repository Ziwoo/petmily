package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.FileUtil;

public class BreedDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		//BreedDao의 getBreed에 br_num을 전달해서 한 건의 
		//레코드를 자바빈에 담아 반환
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		BreedDao dao = BreedDao.getInstance();
		Breed breed = dao.getBreed(br_num);

		//로그인한 아이디와 작성자 아이디가 다를경우
		if(user_id!=null && !user_id.equals(breed.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 삭제할 수 없습니다.");
			return "/views/common/notice.jsp";
		}

		boolean check = false;

		//자바빈이 null이 아닐 경우 전송된 비밀번호를 받아서 
		//비밀번호체크
		if(breed!=null){
			request.setCharacterEncoding("utf-8");
			String br_passwd = request.getParameter("br_passwd");
			//비밀번호 일치 여부 체크
			check = breed.isCheckedPasswd(br_passwd);
		}

		//check가 true이면 BreedDao의 deleteBreed에  br_num 전달
		if(check){
			//글 삭제
			dao.deleteBreed(br_num);
			//파일삭제
			if(breed.getBr_pic()!=null){
				FileUtil.removeFile(request, breed.getBr_pic());
			}
		}

		//check를 request에 저장
		request.setAttribute("check", check);

		return "/views/breed/breedDelete.jsp";
	}

}
