package kr.data.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.data.dao.DataDao;
import kr.data.domain.Data;
import kr.util.FileUtil;

public class DataUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int pd_num = Integer.parseInt(multi.getParameter("pd_num"));
		String pd_pic = multi.getFilesystemName("pd_pic");
		
		DataDao dao = DataDao.getInstantce();
		
		//수정 전 데이터
		Data dbData = dao.getData(pd_num);
		
		//로그인한 아이디와 글 작성자 아이디가 다를 경우
		if(user_id!=null && !user_id.equals(dbData.getMem_id())){
			if(pd_pic!=null){
				FileUtil.removeFile(request, pd_pic);
			}
			request.setAttribute("accessMsg", "타인의 글은 수정할 수 없습니다.");
			return "/views/common/notice.jsp";
		}
		
		//전송된 정보 저장
		Data data = new Data();
		data.setPd_num(pd_num);
		data.setPd_title(multi.getParameter("pd_title"));
		data.setPd_content(multi.getParameter("pd_content"));
		data.setPd_passwd(multi.getParameter("pd_passwd"));
		if(pd_pic!=null){//새 이미지로 교체
			data.setPd_pic(FileUtil.rename(request, pd_pic));
		}else{//전송된 이미지가 없을 경우 기존 이미지 유지
			data.setPd_pic(dbData.getPd_pic());
		}
		
		dao.updateData(data);
		
		if(pd_pic!=null){//새 이미지로 교체할 때 원래 이미지 제거
			FileUtil.removeFile(request, dbData.getPd_pic());
		}
			
		return "redirect:/data/dataList.do";
	}

}
