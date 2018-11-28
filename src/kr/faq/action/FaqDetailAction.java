package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.faq.dao.FaqDao;
import kr.faq.domain.Faq;
import kr.util.StringUtil;

public class FaqDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�� ��ȣ ��ȯ
				int num = Integer.parseInt(request.getParameter("faq_num"));
				FaqDao dao = FaqDao.getInstance();
				
				//��ȸ�� ����
				dao.updateReadcount(num);
				
				//�� ��ȣ�� ��Ī�Ǵ� ���ڵ�(������) ��ȯ
				Faq faq = dao.getFaq(num);
				
				//������ HTML ����
				faq.setFaq_subject(StringUtil.useNoHtml(faq.getFaq_subject()));
				//������ HTML ���� �ٹٲ� ó��
				faq.setFaq_content(StringUtil.useBrNoHtml(faq.getFaq_content()));
				
				request.setAttribute("faq", faq);
				
				return "/views/board/detail.jsp";
	}

}
