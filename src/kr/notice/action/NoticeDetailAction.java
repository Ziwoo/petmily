package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.domain.Notice;
import kr.util.StringUtil;

public class NoticeDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�� ��ȣ ��ȯ
				int num = Integer.parseInt(request.getParameter("nb_num"));
				NoticeDao dao = NoticeDao.getInstance();
				
				//��ȸ�� ����
				dao.updateReadcount(num);
				
				//�� ��ȣ�� ��Ī�Ǵ� ���ڵ�(������) ��ȯ
				Notice nb = dao.getNotice(num);
				
				//������ HTML ����
				nb.setNb_subject(StringUtil.useNoHtml(nb.getNb_subject()));
				//������ HTML ���� �ٹٲ� ó��
				nb.setNb_content(StringUtil.useBrNoHtml(nb.getNb_content()));
				
				request.setAttribute("notice", nb);
				
				return "/views/board/detail.jsp";
	}

}
