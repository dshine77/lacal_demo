package com.ds.demo.moc.Aop.mocaop;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Order.java
 * @Package com.ds.demo.moc.Aop.mocaop
 * @Description
 * @date 2021 03-14 1:27.
 */
public class Order implements Iorder{

	int state = 0;

	@Override
	public void pay() throws InterruptedException {
		Thread.sleep (50);
		this.state = 1;
	}

	@Override
	public void show() {
		System.out.println("order states:" + this.state);

	}
}
