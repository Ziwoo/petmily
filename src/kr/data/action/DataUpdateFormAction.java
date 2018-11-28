package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;

public class DataUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		//�α����� �Ǿ� ���� ������ �α����Ϸ� ������.
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		DataDao dao = DataDao.getInstantce();
		Data data = dao.getData(pd_num);
		
		//(�α����� ��)Ÿ���� ���� �����ϴ� ���� ����
		if(user_id!=null && !user_id.equals(data.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("data", data);
		
		return "/views/data/dataUpdateForm.jsp";
	}

}
