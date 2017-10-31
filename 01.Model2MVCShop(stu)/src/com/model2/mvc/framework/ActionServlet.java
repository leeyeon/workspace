package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		// actionmapping.properties 파일 로딩...
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		// url parsing
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		// ~~~.do 호출
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path);
			// 서블릿의 정보를 서블릿이 바껴도 계속 가지고 가겠다~
			action.setServletContext(this.getServletContext());
			
			// action.excute가 return하는 string parsing해서 다음 페이지를 지정.
			String resultPage=action.execute(request, response);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			// 각각의 Action 별로 return 되는 String 값에 전송방식을 적어주면 나눠서 실행되는군.
			// String.startsWith(str) ... str로 시작되면 true/ 아니면 false
			if(resultPage.startsWith("forward:")) {
				HttpUtil.forward(request, response, result);
			}
			else {
				HttpUtil.redirect(response, result);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}