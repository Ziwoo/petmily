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

		//글 번호 반환
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		DataDao dao = DataDao.getInstantce();
		
		//조회수 증가
		//dao.updateReadcount(pd_num);
		
		//글 번호와 매칭되는 레코드(데이터) 반환
		Data data = dao.getData(pd_num);
		
		//제목은 HTML 불허
		data.setPd_title(StringUtil.useNoHtml(data.getPd_title()));
		//내용은 HTML 불허 줄바꿈 처리
		data.setPd_content(StringUtil.useBrNoHtml(data.getPd_content()));
	
		request.setAttribute("data", data);
		
		return "/views/data/dataDetail.jsp";
	}

}
