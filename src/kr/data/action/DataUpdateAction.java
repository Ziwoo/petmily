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
		
		//���� �� ������
		Data dbData = dao.getData(pd_num);
		
		//�α����� ���̵�� �� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(dbData.getMem_id())){
			if(pd_pic!=null){
				FileUtil.removeFile(request, pd_pic);
			}
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		//���۵� ���� ����
		Data data = new Data();
		data.setPd_num(pd_num);
		data.setPd_title(multi.getParameter("pd_title"));
		data.setPd_content(multi.getParameter("pd_content"));
		data.setPd_passwd(multi.getParameter("pd_passwd"));
		if(pd_pic!=null){//�� �̹����� ��ü
			data.setPd_pic(FileUtil.rename(request, pd_pic));
		}else{//���۵� �̹����� ���� ��� ���� �̹��� ����
			data.setPd_pic(dbData.getPd_pic());
		}
		
		dao.updateData(data);
		
		if(pd_pic!=null){//�� �̹����� ��ü�� �� ���� �̹��� ����
			FileUtil.removeFile(request, dbData.getPd_pic());
		}
			
		return "redirect:/data/dataList.do";
	}

}
