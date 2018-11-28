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
		
		//수정 전 데이터
		Notice dbBoard = dao.getNotice(nb_num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(dbBoard.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//전송된 정보 저장
		Notice nb = new Notice();
		nb.setNb_num(nb_num);
		nb.setNb_subject(multi.getParameter("nb_subject"));
		nb.setNb_content(multi.getParameter("nb_content"));
		nb.setNb_passwd(multi.getParameter("nb_passwd"));
		
		if(filename!=null){//새 이미지로 교체
			nb.setFilename(FileUtil.rename(request, filename));
		}else{//전송된 이미지가 없을 경우 기존 이미지 유지
			nb.setFilename(dbBoard.getFilename());
		}
		
		dao.updateNotice(nb);
		
		if(filename!=null){//새 이미지로 교체할 때 원래 이미지 제거
			FileUtil.removeFile(request, dbBoard.getFilename());
		} 
		
		return "redirect:/board/list.do";
	}

}
