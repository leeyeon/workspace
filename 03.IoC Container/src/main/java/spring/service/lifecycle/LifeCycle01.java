package spring.service.lifecycle;

import org.springframework.beans.factory.BeanNameAware;

public class LifeCycle01 implements BeanNameAware {

	public LifeCycle01() {
		System.out.println("\n:: "+ getClass().getName() + " :: default constructer");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("\n:: "+ getClass().getName() + " :: setBeanName() start");
		System.out.println("xml에 정의된 beanName ::" + name);
		System.out.println("\n:: "+ getClass().getName() + " :: setBeanName() end");
	}
	
	public void init() {
		System.out.println("init() method");
	}
	
	public void destroy() {
		System.out.println("destroy{} method");
	}

}
