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
		//글 번호 반환
				int num = Integer.parseInt(request.getParameter("nb_num"));
				NoticeDao dao = NoticeDao.getInstance();
				
				//조회수 증가
				dao.updateReadcount(num);
				
				//글 번호와 매칭되는 레코드(데이터) 반환
				Notice nb = dao.getNotice(num);
				
				//제목은 HTML 불허
				nb.setNb_subject(StringUtil.useNoHtml(nb.getNb_subject()));
				//내용은 HTML 불허 줄바꿈 처리
				nb.setNb_content(StringUtil.useBrNoHtml(nb.getNb_content()));
				
				request.setAttribute("notice", nb);
				
				return "/views/board/detail.jsp";
	}

}
