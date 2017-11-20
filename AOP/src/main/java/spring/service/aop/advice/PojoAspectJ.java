package spring.service.aop.advice;

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
	
	public void work() {
		System.out.println("work() pointcut call..");
	}

	public void before(JoinPoint joinPoint) throws Throwable {
		
		System.out.println("[before Log] "+getClass()+".before() start");
		System.out.println("[before Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[before Log] targetObject call Method :: "+joinPoint.getSignature().getName());
		if( joinPoint.getArgs().length != 0 ) {
			System.out.println("[before Log] targetObject method Àü´Þ argument "+joinPoint.getArgs()[0]);
		}
		System.out.println("[before Log] "+getClass()+".before() end");
	}

	public void afterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {
		
		System.out.println("[after Log] "+getClass()+".before() start");
		System.out.println("[after Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[after Log] targetObject call Method :: "+joinPoint.getSignature().getName());
		System.out.println("[after Log] Å¸°Ù °´Ã¼ È£Ãâ ÈÄ return Value "+returnValue);
		System.out.println("[after Log] "+getClass()+".before() end");
	}

	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("[Around Log] "+getClass()+".before() start");
		System.out.println("[Around Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Around Log] targetObject call Method :: "+joinPoint.getTarget().getClass().getName());
		if( joinPoint.getArgs().length != 0 ) {
			System.out.println("[before Log] targetObject method Àü´Þ argument "+joinPoint.getArgs()[0]);
		}

		// targetObject Method call
		Object obj = joinPoint.proceed();
		System.out.println("[Around Log] Å¸°Ù °´Ã¼ È£Ãâ ÈÄ return Value " + obj);
		System.out.println("[Around Log] "+getClass()+".before() end");
		
		return obj;
	}
	
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		System.out.println("[Exception] "+getClass()+".before() start");
		System.out.println("[Exception] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Exception] targetObject call Method :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Exception] Exception ¹ß»ý... ");
		System.out.println("[Exception] Exception Message :: "+throwable.getMessage());
		System.out.println("[Exception] "+getClass()+".before() end");
	}
}
