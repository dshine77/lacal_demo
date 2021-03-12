package com.ds.demo.moc.Aop;

/**
 * @author Administrator
 * @version V1.0
 * @Title: ManWaiter.java
 * @Package com.ds.demo.moc.Aop
 * @Description
 * @date 2019 12-04 16:53.
 */
public class ManWaiter implements Waiter {
	@Override
	public void service() {
		System.out.println("服务中");
	}
}
