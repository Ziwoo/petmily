package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.domain.Notice;
import kr.util.FileUtil;

public class NoticeWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		Notice nb = new Notice();
		nb.setNb_subject(multi.getParameter("nb_subject"));
		nb.setNb_content(multi.getParameter("nb_content"));
		nb.setNb_passwd(multi.getParameter("nb_passwd"));
		//파일명 변경
		nb.setFilename(FileUtil.rename(request,multi.getFilesystemName("filename")));
		nb.setMem_id(user_id);
		
		NoticeDao dao = NoticeDao.getInstance();
		dao.insertNotice(nb);
		
		return "/views/board/write.jsp";
	}

}
