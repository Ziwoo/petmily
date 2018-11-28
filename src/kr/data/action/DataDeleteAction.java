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
		
		//�α����� ���̵�� �� �ۼ��� ���̵� �ٸ� ���
		if(user_id!=null && !user_id.equals(data.getMem_id())){
			request.setAttribute("accessMsg", "Ÿ���� ���� ������ �� �����ϴ�.");
			return "/views/common/notice.jsp";
		}
		
		boolean check = false;

		//�ڹٺ��� null�� �ƴ� ��� ���۵� ��й�ȣ�� �޾Ƽ� 
		//��й�ȣüũ
		if(data!=null){
			request.setCharacterEncoding("utf-8");
			String pd_passwd = request.getParameter("pd_passwd");
			//��й�ȣ ��ġ ���� üũ
			check = data.isCheckedPasswd(pd_passwd);
		}
		
		if(check){
			//�� ����
			dao.deleteData(pd_num);
			
			//���� ����
			if(data.getPd_pic()!=null){
				FileUtil.removeFile(request, data.getPd_pic());
			}
		}
		
		//check�� request�� ����
		request.setAttribute("check", check);
		
		return "/views/data/dataDelete.jsp";
	}

}
