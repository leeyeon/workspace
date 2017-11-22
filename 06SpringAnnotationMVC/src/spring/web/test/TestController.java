package spring.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	public TestController() {
		System.out.println("TestController Constructor");
	}
	
	@RequestMapping("/testView01.do")
	public ModelAndView testView01() {
		System.out.println("testView01...");
		
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping("/testView02.do")
	public String testView02() {
		System.out.println("testView02...");
		
		return "test/testView.jsp";
	}
	
	@RequestMapping("/testView03.do")
	public String testView03(@RequestParam("abc") int no, 
							@RequestParam("def") String name) {
		System.out.println("testView03...");
		System.out.println("no ::" + no + " && name :: "+name);
	
		return "test/testView.jsp";
	}
	
	@RequestMapping("/testView04.do")
	public String testView04(@RequestParam("abc") int no, 
							@RequestParam(value = "def", required = false) String name) {
		System.out.println("testView04...");
		System.out.println("no ::" + no + " && name :: "+name);
		
		return "test/testView.jsp";
	}
	
	@RequestMapping("/testView05.do")
	public String testView05(@RequestParam(value = "abc", defaultValue = "1") int no, 
							@RequestParam(value = "def", required = false) String name) {
		System.out.println("testView05...");
		System.out.println("no ::" + no + " && name :: "+name);
		
		return "test/testView.jsp";
	}
}
