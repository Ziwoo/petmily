package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;
import kr.util.StringUtil;

public class DataDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//�� ��ȣ ��ȯ
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		DataDao dao = DataDao.getInstantce();
		
		//��ȸ�� ����
		//dao.updateReadcount(pd_num);
		
		//�� ��ȣ�� ��Ī�Ǵ� ���ڵ�(������) ��ȯ
		Data data = dao.getData(pd_num);
		
		//������ HTML ����
		data.setPd_title(StringUtil.useNoHtml(data.getPd_title()));
		//������ HTML ���� �ٹٲ� ó��
		data.setPd_content(StringUtil.useBrNoHtml(data.getPd_content()));
	
		request.setAttribute("data", data);
		
		return "/views/data/dataDetail.jsp";
	}

}
