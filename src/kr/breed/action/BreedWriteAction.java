package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.FileUtil;

public class BreedWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		//유틸리티 클래스를 이용한 업로드 처리
		// enctype="multipart/form-data" 로 명시한 경우에는
		// request.getParameter대신 MultipartRequest의 도움을 받아야한다
		MultipartRequest multi = FileUtil.createFile(request);
		Breed breed = new Breed();
		breed.setBr_subject(multi.getParameter("br_subject"));
		breed.setBr_content(multi.getParameter("br_content"));
		breed.setBr_passwd(multi.getParameter("br_passwd"));
		breed.setMem_id(user_id);
		//파일명 변경
		breed.setBr_pic(FileUtil.rename(request, multi.getFilesystemName("br_pic")));
		
		BreedDao dao = BreedDao.getInstance();
		dao.insertBreed(breed);
		
		return "/views/breed/breedWrite.jsp";
	}

}
