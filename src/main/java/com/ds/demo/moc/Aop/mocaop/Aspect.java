/**
 * @Author Dai.sha
 * @create 2021/3/14 1:29
 */
package com.ds.demo.moc.Aop.mocaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Aspect.java
 * @Package com.ds.demo.moc.Aop.mocaop
 * @Description
 * @date 2021 03-14 1:29.
 */
public interface Aspect {

	void before();

	void after();

	static<T> T getProxy(Class<T> clazz, String ...classPath) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException {

		List<Aspect> aspects =
				Arrays.stream (classPath).map (name -> {
			try {
				Class <?> aClass = Class.forName (name);
				return (Aspect) aClass.getConstructor ().newInstance ();
			} catch (Exception e) {
				return null;
			}
		}).filter (o -> o!= null).collect (Collectors.toList ());
		T inst = clazz.getConstructor ().newInstance ();
		return (T) Proxy.newProxyInstance (clazz.getClassLoader (), clazz.getInterfaces (),
				new InvocationHandler () {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				for (Aspect aspect : aspects) {
					aspect.before ();
				}
				Object result  = method.invoke (inst, args);
				for (Aspect aspect : aspects) {
					aspect.after ();
				}
				return result;
			}
		});

	}
}
