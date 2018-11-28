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
		
		//���� �� ������
		Breed dbBreed = dao.getBreed(br_num);
		
		//�α����� ���̵�� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(dbBreed.getMem_id())){
			if(br_pic!=null){
				FileUtil.removeFile(request, br_pic);
			}
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		//���۵� ���� ����
		Breed breed = new Breed();
		breed.setBr_num(br_num);
		breed.setBr_subject(multi.getParameter("br_subject"));
		breed.setBr_content(multi.getParameter("br_content"));
		breed.setBr_passwd(multi.getParameter("br_passwd"));
		if(br_pic!=null){//�� �̹����� ��ü
			breed.setBr_pic(FileUtil.rename(request, br_pic));
		}else{//���۵� �̹����� ���� ���
			breed.setBr_pic(dbBreed.getBr_pic());
		}
		
		dao.updateBreed(breed);
		
		if(br_pic!=null){//�� �̹����� ��ü �� ��, ���� �̹��� ����
			FileUtil.removeFile(request, dbBreed.getBr_pic());
		}
		
		//���� �� ������� ���ư���
		return "redirect:/breed/breedList.do";
	}

}
