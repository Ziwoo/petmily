package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.domain.Notice;
import kr.util.FileUtil;

public class NoticeUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int nb_num = Integer.parseInt(request.getParameter("nb_num"));
		String filename = multi.getFilesystemName("filename");
		
		NoticeDao dao = NoticeDao.getInstance();
		
		//���� �� ������
		Notice dbBoard = dao.getNotice(nb_num);
		
		//�α����� ���̵�� �� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(dbBoard.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		//���۵� ���� ����
		Notice nb = new Notice();
		nb.setNb_num(nb_num);
		nb.setNb_subject(multi.getParameter("nb_subject"));
		nb.setNb_content(multi.getParameter("nb_content"));
		nb.setNb_passwd(multi.getParameter("nb_passwd"));
		
		if(filename!=null){//�� �̹����� ��ü
			nb.setFilename(FileUtil.rename(request, filename));
		}else{//���۵� �̹����� ���� ��� ���� �̹��� ����
			nb.setFilename(dbBoard.getFilename());
		}
		
		dao.updateNotice(nb);
		
		if(filename!=null){//�� �̹����� ��ü�� �� ���� �̹��� ����
			FileUtil.removeFile(request, dbBoard.getFilename());
		} 
		
		return "redirect:/board/list.do";
	}

}
