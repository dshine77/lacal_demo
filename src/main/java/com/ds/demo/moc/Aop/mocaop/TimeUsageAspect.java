package com.ds.demo.moc.Aop.mocaop;

/**
 * @author Administrator
 * @version V1.0
 * @Title: TimeUsageAspect.java
 * @Package com.ds.demo.moc.Aop.mocaop
 * @Description
 * @date 2021 03-14 1:30.
 */
public class TimeUsageAspect implements Aspect{

	long start;
	@Override
	public void before() {
		start = System.currentTimeMillis ();
	}

	@Override
	public void after() {
		long usage = System.currentTimeMillis () - start;
		System.out.println ("time usage :" + usage);

	}
}
