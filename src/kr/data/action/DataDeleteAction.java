package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;
import kr.util.FileUtil;

public class DataDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		int pd_num = Integer.parseInt(request.getParameter("pd_num"));
		
		DataDao dao = DataDao.getInstantce();
		Data data = dao.getData(pd_num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(data.getMem_id())){
			request.setAttribute("accessMsg", "타인의 글은 삭제할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		boolean check = false;

		//자바빈이 null이 아닐 경우 전송된 비밀번호를 받아서 
		//비밀번호체크
		if(data!=null){
			request.setCharacterEncoding("utf-8");
			String pd_passwd = request.getParameter("pd_passwd");
			//비밀번호 일치 여부 체크
			check = data.isCheckedPasswd(pd_passwd);
		}
		
		if(check){
			//글 삭제
			dao.deleteData(pd_num);
			
			//파일 삭제
			if(data.getPd_pic()!=null){
				FileUtil.removeFile(request, data.getPd_pic());
			}
		}
		
		//check를 request에 저장
		request.setAttribute("check", check);
		
		return "/views/data/dataDelete.jsp";
	}

}
