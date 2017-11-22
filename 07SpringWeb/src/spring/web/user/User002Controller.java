package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.impl.UserDAO;

//@Controller
public class User002Controller {

	public User002Controller() {
		System.out.println("controllere constructor");
	}

	@RequestMapping("/logon.do")
	public ModelAndView logon(HttpSession session) {
		System.out.println("\n ==> logon() start");
		
		if(session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User());
		}
		
		User sessionUser = (User)session.getAttribute("sessionUser");
		
		String viewName = "/user002/logon.jsp";
		
		if(sessionUser.isActive()) {
			viewName = "/user002/home.jsp";
		}
		
		System.out.println("action :: "+viewName);
		
		String message = null;
		if(viewName.equals("/ueser002/home.jsp")) {
			message = "[logon()] WELCOME";
		} else {
			message = "[logon()] 아이디,패스워드를 3자 이상 입력하세요.";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("\n ==> logon() end");
		
		return modelAndView;
	}
	
	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {
		System.out.println("\n ==> home() start");
		
		if(session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", new User());
		}
		
		User sessionUser = (User)session.getAttribute("sessionUser");
		
		String viewName = "/user002/logon.jsp";
		
		if(sessionUser.isActive()) {
			viewName = "/user002/home.jsp";
		}
		
		System.out.println("action :: "+viewName);
		
		String message = null;
		if(viewName.equals("/ueser002/home.jsp")) {
			message = "[home()] WELCOME";
		} else {
			message = "[home()] 아이디,패스워드를 3자 이상 입력하세요.";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("\n ==> home() end");
		
		return modelAndView;
	}
	
	@RequestMapping("/logonAction.do")
	public ModelAndView logonAction(@ModelAttribute("user") User user,
									HttpSession session) {
		System.out.println("\n ==> logonAction() start");
		
		if(session.isNew() || session.getAttribute("sessionUser") == null) {
			session.setAttribute("sessionUser", user);
		}
		
		User sessionUser = (User)session.getAttribute("sessionUser");
		
		String viewName = "/user002/logon.jsp";
		
		if(sessionUser.isActive()) {
			viewName = "/user002/home.jsp";
		} else {
			UserDAO userDAO = new UserDAO();
			userDAO.getUser(user);
			
			if(user.isActive()) {
				viewName = "/user002/home.jsp";
				session.setAttribute("sessionUser", user);
			}
		}
		
		System.out.println("action :: "+viewName);
		
		String message = null;
		if(viewName.equals("/ueser002/home.jsp")) {
			message = "[logonAction()] WELCOME";
		} else {
			message = "[logonAction()] 아이디,패스워드를 3자 이상 입력하세요.";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message", message);
		
		System.out.println("\n ==> logonAction() end");
		
		return modelAndView;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {
		System.out.println("\n ==> logout() start");
		
		session.removeAttribute("sessionUser");
		
		String message = "[logout()] 아이디,패스워드를 3자 이상 입력하세요.";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user002/logon.jsp");
		modelAndView.addObject("message", message);
		
		System.out.println("\n ==> logout() end");
		
		return modelAndView;
	}
}
