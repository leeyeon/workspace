import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 FileName  : ServletTest.java
	�� Servlet Life Cycle  ���� �� Ȯ��
*/
public class HelloServletdoGet extends HttpServlet{

	//Client request �� ���� ȣ��Ǵ� service() method
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		System.out.println("::servlet doGet() ����");
		System.out.println("Client IP: "+req.getRemoteAddr());

		//res.setContentType("text/html");
		res.setContentType("text/html;charset=EUC-KR");

		PrintWriter out = res.getWriter();

		out.println("<html>");
		out.println("<head><title>hello Servlet</title></head>");
		out.println("<body>");
		out.println("English: HelloServlet");
		out.println("<p>");
		out.println("Korea: ��� ������");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();

		System.out.println("::servlet doGet() ����");


	}

}//end of class