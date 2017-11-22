package spring.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;

@Controller
public class UserController {

	public UserController() {
		System.out.println("UserController Constructer");
	}

	@RequestMapping("/logonViewModelAndView.do")
	public ModelAndView logonViewModelAndView() {
		System.out.println("logonViewModelAndView...");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logonView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logon01.do")
	public ModelAndView logon01(HttpServletRequest request) {
		System.out.println("logon01 ...");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logon02.do")
	public ModelAndView logon02(HttpServletRequest request, HttpSession session) {
		System.out.println("logon02 ...");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logon03.do")
	public ModelAndView logon03(@RequestParam("userId") String userId,
								@RequestParam("password") String password,
								HttpServletRequest request, HttpSession session) {
		System.out.println("logon03 ...");
		
		request.setAttribute("userId", userId);
		
		session.setAttribute("password", password);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logon04.do")
	public ModelAndView logon04(@ModelAttribute("user") User user,
								HttpServletRequest request, HttpSession session) {
		System.out.println("logon04 ...");
		
		request.setAttribute("userId", user.getUserId());
		
		session.setAttribute("password", user.getPassword());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logon05.do")
	public ModelAndView logon05(@ModelAttribute("user") User user) {
		System.out.println("logon05 ...");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "logon05 Controller 구현 연습");
		
		modelAndView.setViewName("/user/logonResult.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/logonViewString.do")
	public String logonViewString() {
		System.out.println("logonViewString ...");
		
		return "/user/logonView.jsp";
	}
	
	@RequestMapping("/logon06.do")
	public String logon06(@ModelAttribute("user") User user,
						HttpServletRequest request,
						HttpSession session,
						Map<String, String> map) {
		System.out.println("logon06 ...");

		request.setAttribute("userId", user.getUserId());
		
		session.setAttribute("password", user.getPassword());
		
		map.put("massage", "logon06 Controller .....!!!!!");
		
		return "/user/logonResult.jsp";
	}
	
	@RequestMapping("/logon07.do")
	public String logon07(@RequestParam("userId") String userId,
						@RequestParam("password") String password,
						Model model) {
		System.out.println("logon07 ...");
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		
		model.addAttribute("user", user);
		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		model.addAttribute("massage", "logon07 Controller .....!!!!!");
		
		return "/user/logonResult.jsp";
	}
}






