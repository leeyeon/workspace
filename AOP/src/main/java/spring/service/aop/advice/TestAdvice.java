package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class TestAdvice implements MethodBeforeAdvice,AfterReturningAdvice,
									ThrowsAdvice, MethodInterceptor {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("[before Log] "+getClass()+".before() start");
		System.out.println("[before Log] targetObject call Method :: "+method);
		if( args.length != 0 ) {
			System.out.println("[before Log] targetObject method ���� argument "+args[0]);
		}
		System.out.println("[before Log] "+getClass()+".before() end");
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println("[after Log] "+getClass()+".before() start");
		System.out.println("[after Log] targetObject call Method :: "+method);
		System.out.println("[after Log] Ÿ�� ��ü ȣ�� �� return Value"+returnValue);
		System.out.println("[after Log] "+getClass()+".before() end");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println("[Around Log] "+getClass()+".before() start");
		System.out.println("[Around Log] targetObject call Method :: "+invocation.getMethod());
		if( invocation.getArguments().length != 0 ) {
			System.out.println("[before Log] targetObject method ���� argument "+invocation.getArguments()[0]);
		}

		// targetObject Method call
		Object obj = invocation.proceed();
		System.out.println("[Around Log] Ÿ�� ��ü ȣ�� �� return Value" + obj);
		System.out.println("[Around Log] "+getClass()+".before() end");
		
		return obj;
	}
	
	public void afterThrowing(Throwable throwable) {
		System.out.println("[Exception] "+getClass()+".before() start");
		System.out.println("[Exception] Exception �߻�... ");
		System.out.println("[Exception] Exception Message :: "+throwable.getMessage());
		System.out.println("[Exception] "+getClass()+".before() end");
	}
}
