package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;
import kr.util.FileUtil;

public class DataWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		Data data = new Data();
		data.setPd_title(multi.getParameter("pd_title"));
		data.setPd_content(multi.getParameter("pd_content"));
		data.setPd_passwd(multi.getParameter("pd_passwd"));
		//파일명 변경
		data.setPd_pic(FileUtil.rename(request,multi.getFilesystemName("pd_pic")));
		data.setMem_id(user_id);
		
		DataDao dao = DataDao.getInstantce();
		dao.insertData(data);
		
		return "/views/data/dataWrite.jsp";
	}

}