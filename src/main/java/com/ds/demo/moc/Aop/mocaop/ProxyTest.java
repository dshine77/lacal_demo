package com.ds.demo.moc.Aop.mocaop;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Administrator
 * @version V1.0
 * @Title: ProxyTest.java
 * @Package com.ds.demo.moc.Aop.mocaop
 * @Description
 * @date 2021 03-14 10:02.
 */
public class ProxyTest {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
	    Iorder order = Aspect.getProxy (Order.class,"com.ds.demo.moc.Aop.mocaop.TimeUsageAspect");
	    order.pay ();
	    order.show ();
	}
}
