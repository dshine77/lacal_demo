package com.ds.demo.moc.controller;

import com.ds.demo.moc.service.A;
import com.ds.demo.moc.service.B;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Test.java
 * @Package com.ds.demo.moc.controller
 * @Description
 * @date 2019 12-03 16:40.
 */
public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		test.test1();
	}


	public void test1(){
		ClassLoader loader = this.getClass().getClassLoader();

		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("你好？？");
				return "Hello";
			}
		};

		Object obj = Proxy.newProxyInstance(loader, new Class[]{A.class,B.class}, handler);

		A a = (A) obj;
		B b = (B) obj;

		a.toString();
		b.getClass();

		Object object = a.aaa("Hello", 100);
		System.out.println(object.getClass());
		System.out.println(object);


	}
}
