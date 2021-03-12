package com.ds.demo.moc.Aop;

import org.junit.Test;

public class Demo3 {
	@Test
	public void test1() {

		ProxyFactory proxyFactory = new ProxyFactory();//创建工厂
		proxyFactory.setTargetObj(new ManWaiter());//设置目标对象
		//设置前置增强
		proxyFactory.setBeforeAdvice(new BeforeAdvice() {
			@Override
			public void before() {
				System.out.println("客户你好");
			}
		});
		//设置后置增强
		proxyFactory.setAfterAdvice(new AfterAdvice() {
			@Override
			public void after() {
				System.out.println("客户再见");
			}
		});
		Waiter waiter = (Waiter) proxyFactory.createProxy();
		waiter.service();

	}
}
