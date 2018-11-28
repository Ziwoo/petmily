package kr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	
	private Map<String,Action> commandMap =
			new HashMap<String,Action>();
	
	@Override
	public void init(ServletConfig config)
			                   throws ServletException{
		Properties pr = new Properties();
		//"/WEB-INF/ActionMap.properties" 반환
		String props = config.getInitParameter("propertyConfig");
		//"/WEB-INF/ActionMap.properties"의 절대경로 구함
		String path = config.getServletContext().getRealPath(props);
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(path);
			//파일 스트림을 Properties 객체에 넘겨 key와 value로 구분
			pr.load(fis);
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(fis!=null)try{fis.close();}catch(IOException e){}
		}
	
		//Properties 객체에서 key구하기
		Iterator keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();//key
			String className = pr.getProperty(command);//value
			
			try{
				//문자열을 이용해 클래스를 찾아 Class 타입으로 반환
				Class commandClass = Class.forName(className);
				//객체로 생성
				Object commandInstance = commandClass.newInstance();
				
				System.out.println(command + ", " + commandInstance);
				
				//HashMap에 key와 value로 등록
				commandMap.put(command, (Action)commandInstance);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	 
	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response)
	                throws ServletException,IOException{
		requestPro(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			              HttpServletResponse response)
	                throws ServletException,IOException{
		requestPro(request,response);
	}
	private void requestPro(HttpServletRequest request,
			                HttpServletResponse response)
	                throws ServletException,IOException{
		String view = null;
		Action com = null;
		
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				command = command.substring(
						request.getContextPath().length());
			}
			
			//HashMap에 key를 넣어서 value(모델 객체)를 얻음
			com = commandMap.get(command);
			view = com.execute(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:")){//리다이렉트
			view = view.substring("redirect:".length());
			response.sendRedirect(request.getContextPath()+view);
		}else{//포워드
			//forward 방식으로 view(jsp) 호출
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}