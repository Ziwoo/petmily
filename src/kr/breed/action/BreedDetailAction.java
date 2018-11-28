package kr.breed.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.breed.dao.BreedDao;
import kr.breed.domain.Breed;
import kr.controller.Action;
import kr.util.StringUtil;

public class BreedDetailAction implements Action{
  
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//�� ��ȣ ��ȯ
		int br_num = Integer.parseInt(request.getParameter("br_num"));
		
		BreedDao dao = BreedDao.getInstance();
		
		//��ȸ�� ����
		dao.updateReadCount(br_num);
		
		//�� ��ȣ�� ��Ī�Ǵ� ���ڵ�(������) ��ȯ
		Breed breed = dao.getBreed(br_num);
		
		//������ HTML����
		breed.setBr_subject(StringUtil.useNoHtml(breed.getBr_subject()));
		//������ HTML ����, �ٹٲ� ó��
		breed.setBr_content(StringUtil.useBrNoHtml(breed.getBr_content()));
		
		request.setAttribute("breed", breed);
		
		return "/views/breed/breedDetail.jsp";
	}

}
