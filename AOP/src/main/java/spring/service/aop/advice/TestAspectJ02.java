package spring.service.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspectJ02{

	public TestAspectJ02() {
		System.out.println("::TestAspect02 Construtor...");
	}
	
	/*
	@Pointcut("execution(* *.getMessage(..))")
	public void work() {
		System.out.println("work() pointcut call..");
	}
	*/
	// 직접 기술 X

	@Before("execution(* *.getMessage(..))")
	public void before(JoinPoint joinPoint) throws Throwable {
		
		System.out.println("[before Log] "+getClass()+".before() start");
		System.out.println("[before Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[before Log] targetObject call Method :: "+joinPoint.getSignature().getName());
		if( joinPoint.getArgs().length != 0 ) {
			System.out.println("[before Log] targetObject method 전달 argument "+joinPoint.getArgs()[0]);
		}
		System.out.println("[before Log] "+getClass()+".before() end");
	}

	@AfterReturning(pointcut="within(spring.service.aop..*)", returning="returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {
		
		System.out.println("[after Log] "+getClass()+".before() start");
		System.out.println("[after Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[after Log] targetObject call Method :: "+joinPoint.getSignature().getName());
		System.out.println("[after Log] 타겟 객체 호출 후 return Value"+returnValue);
		System.out.println("[after Log] "+getClass()+".before() end");
	}

	@Around("execution(* spring.service.aop.*.*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("[Around Log] "+getClass()+".before() start");
		System.out.println("[Around Log] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Around Log] targetObject call Method :: "+joinPoint.getTarget().getClass().getName());
		if( joinPoint.getArgs().length != 0 ) {
			System.out.println("[before Log] targetObject method 전달 argument "+joinPoint.getArgs()[0]);
		}

		// targetObject Method call
		Object obj = joinPoint.proceed();
		System.out.println("[Around Log] 타겟 객체 호출 후 return Value" + obj);
		System.out.println("[Around Log] "+getClass()+".before() end");
		
		return obj;
	}
	
	@AfterThrowing(pointcut="execution(public * *(..))", throwing="throwable")
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		System.out.println("[Exception] "+getClass()+".before() start");
		System.out.println("[Exception] targetObject :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Exception] targetObject call Method :: "+joinPoint.getTarget().getClass().getName());
		System.out.println("[Exception] Exception 발생... ");
		System.out.println("[Exception] Exception Message :: "+throwable.getMessage());
		System.out.println("[Exception] "+getClass()+".before() end");
	}
}
