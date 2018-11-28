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
		
		//BreedDao�� getBreed�� br_num�� �����ؼ� �� ���� 
		//���ڵ带 �ڹٺ� ��� ��ȯ
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		BreedDao dao = BreedDao.getInstance();
		Breed breed = dao.getBreed(br_num);

		//�α����� ���̵�� �ۼ��� ���̵� �ٸ����
		if(user_id!=null && !user_id.equals(breed.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}

		boolean check = false;

		//�ڹٺ��� null�� �ƴ� ��� ���۵� ��й�ȣ�� �޾Ƽ� 
		//��й�ȣüũ
		if(breed!=null){
			request.setCharacterEncoding("utf-8");
			String br_passwd = request.getParameter("br_passwd");
			//��й�ȣ ��ġ ���� üũ
			check = breed.isCheckedPasswd(br_passwd);
		}

		//check�� true�̸� BreedDao�� deleteBreed��  br_num ����
		if(check){
			//�� ����
			dao.deleteBreed(br_num);
			//���ϻ���
			if(breed.getBr_pic()!=null){
				FileUtil.removeFile(request, breed.getBr_pic());
			}
		}

		//check�� request�� ����
		request.setAttribute("check", check);

		return "/views/breed/breedDelete.jsp";
	}

}
