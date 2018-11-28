package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import kr.controller.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//session를 request로부터 반환 받음
		HttpSession session = request.getSession();
		//로그아웃
		session.invalidate();
		return "/views/member/logout.jsp";
	}

}







