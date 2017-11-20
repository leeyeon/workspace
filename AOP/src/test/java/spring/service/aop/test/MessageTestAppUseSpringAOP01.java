package spring.service.aop.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.aop.Message;
import spring.service.aop.handler.LoggingHandler;
import spring.service.aop.impl.MessageImpl;

public class MessageTestAppUseSpringAOP01 {
	
	///Main Method
	public static void main(String[] args) throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/messageservice01.xml");
		
		Message message = (Message)context.getBean("message");
		//System.out.println(message);
		message.setMessage("Hello");
		
		System.out.println("리턴받은 메세지 : "+message.getMessage());
	}
}//end of class