package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	public UserController() {
		System.out.println("controller constructor");
	}

	@RequestMapping
	public String logon(Model model) {
		System.out.println("\n ==> logon() start");
		
		String message = "[logon()] ���̵�,�н����带 3�� �̻� �Է��ϼ���.";

		model.addAttribute("message", message);
		
		System.out.println("\n ==> logon() end");
		
		return "/user/logon.jsp";

	}
	
	@RequestMapping
	public String home(Model model) {
		System.out.println("\n ==> home() start");
		
		String message = "[home()] WELCOME";
		
		model.addAttribute("message", message);
		
		System.out.println("\n ==> home() end");
		
		return "/user/home.jsp";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String logonAction(@ModelAttribute("user") User user,
									HttpSession session,
									Model model) throws Exception  {
		System.out.println("\n ==> logonAction() method = RequestMethod.POST start");

		String viewName = "/user/logon.jsp";

		User returnUser = userService.getUser(user.getUserId());
		if(returnUser.getPassword().equals(user.getPassword())) {
			returnUser.setActive(true);
			user = returnUser;
		}
		
		if(user.isActive()) {
			viewName = "/user/home.jsp";
			session.setAttribute("sessionUser", user);
		}
		
		System.out.println("action :: "+viewName);
		
		String message = null;
		if(viewName.equals("/ueser002/home.jsp")) {
			message = "[logonAction()] WELCOME";
		} else {
			message = "[logonAction()] ���̵�,�н����带 3�� �̻� �Է��ϼ���.";
		}
		
		model.addAttribute("message", message);
		
		System.out.println("\n ==> logonAction() method = RequestMethod.POST end");
		
		return viewName;
	}
	
	@RequestMapping
	public String logout(HttpSession session, Model model) {
		System.out.println("\n ==> logout() start");
		
		session.removeAttribute("sessionUser");
		
		String message = "[logout()] ���̵�,�н����带 3�� �̻� �Է��ϼ���.";
		
		model.addAttribute("message", message);
		
		System.out.println("\n ==> logout() end");
		
		return "/user/logon.jsp";
	}
}
