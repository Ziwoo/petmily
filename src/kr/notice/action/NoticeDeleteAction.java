package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.domain.Notice;
import kr.util.FileUtil;

public class NoticeDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}

		int num = Integer.parseInt(request.getParameter("nb_num"));

		NoticeDao dao = NoticeDao.getInstance();
		Notice nb = dao.getNotice(num);

		//�α����� ���̵�� �� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(nb.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}

		//�� ����
		dao.deleteNotice(num);

		//���� ����
		if(nb.getFilename()!=null){
			FileUtil.removeFile(request, nb.getFilename());
		}

		return "redirect:/board/list.do";
	}

}