package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.FileUtil;

public class BreedUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int br_num = Integer.parseInt(multi.getParameter("br_num"));
		String br_pic = multi.getFilesystemName("br_pic");
		
		BreedDao dao = BreedDao.getInstance();
		
		//수정 전 데이터
		Breed dbBreed = dao.getBreed(br_num);
		
		//로그인한 아이디와 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(dbBreed.getMem_id())){
			if(br_pic!=null){
				FileUtil.removeFile(request, br_pic);
			}
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//전송된 정보 저장
		Breed breed = new Breed();
		breed.setBr_num(br_num);
		breed.setBr_subject(multi.getParameter("br_subject"));
		breed.setBr_content(multi.getParameter("br_content"));
		breed.setBr_passwd(multi.getParameter("br_passwd"));
		if(br_pic!=null){//새 이미지로 교체
			breed.setBr_pic(FileUtil.rename(request, br_pic));
		}else{//전송된 이미지가 없을 경우
			breed.setBr_pic(dbBreed.getBr_pic());
		}
		
		dao.updateBreed(breed);
		
		if(br_pic!=null){//새 이미지로 교체 할 때, 원래 이미지 제거
			FileUtil.removeFile(request, dbBreed.getBr_pic());
		}
		
		//수정 후 목록으로 돌아가기
		return "redirect:/breed/breedList.do";
	}

}
