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
		
		//로그인이 되어 있지 않으면 로그인하러 가세요.
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		DataDao dao = DataDao.getInstantce();
		Data data = dao.getData(pd_num);
		
		//(로그인을 한)타인이 글을 수정하는 것을 막음
		if(user_id!=null && !user_id.equals(data.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		request.setAttribute("data", data);
		
		return "/views/data/dataUpdateForm.jsp";
	}

}
