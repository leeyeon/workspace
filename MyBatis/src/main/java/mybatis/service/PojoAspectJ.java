package mybatis.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


public class PojoAspectJ{

	public PojoAspectJ() {
		System.out.println("::TestAspect01 Construtor...");
	}

	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("[before Log] "+getClass()+".invoke() start");
		System.out.println("[before Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[before Log] targetObject call Method :: "+joinPoint.getTarget().getClass().getName());
		if( joinPoint.getArgs().length != 0 ) {
			System.out.println("[before Log] targetObject method Àü´Þ argument "+joinPoint.getArgs()[0]);
		}
		
		System.out.println("============================================== \n");
		
		// targetObject Method call
		Object obj = joinPoint.proceed();
		
		System.out.println("[after Log] Å¸°Ù °´Ã¼ È£Ãâ ÈÄ return Value " + obj);
		System.out.println("[after Log] "+getClass()+".before() end");
		
		System.out.println("============================================== \n");
		
		return obj;
	}

}
