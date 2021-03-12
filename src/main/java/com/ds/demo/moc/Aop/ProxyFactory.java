package com.ds.demo.moc.Aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @version V1.0
 * @Title: ProxyFactory.java
 * @Package com.ds.demo.moc.Aop
 * @Description
 * @date 2019 12-04 16:58.
 */

/**
 * 1、创建代理工厂
 * 2、给工厂设置目标对象、前置增强、后置增强
 * 3、调用creatProxy()得到代理对象
 * 4、执行代理对象方法时，先执行前置增强，然后是目标方法，最后是后置增强
 */
//其实在Spring中的AOP的动态代理实现的一个织入器也是叫做ProxyFactory
public class ProxyFactory {

	private Object targetObj;//目标对象
	private BeforeAdvice beforeAdvice;
	private AfterAdvice afterAdvice;

	/**
	 * 用来生成代理对象
	 * @return
	 */
	public Object createProxy(){

		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				/**
				 * 在调用代理对象的方法时，会执行这里的内容
				 */
				if(beforeAdvice != null) {
					beforeAdvice.before();
				}
				Object result = method.invoke(targetObj, args);//调用目标对象的目标方法
				//执行后续增强
				if (afterAdvice != null){
					afterAdvice.after();
				}

				//返回目标对象的返回值
				return result;
			}
		};

		/**
		 * 2、得到代理对象
		 */
		Object proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), handler);
		return proxyObj;
	}

	public Object getTargetObj() {
		return targetObj;
	}

	public void setTargetObj(Object targetObj) {
		this.targetObj = targetObj;
	}

	public BeforeAdvice getBeforeAdvice() {
		return beforeAdvice;
	}

	public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
		this.beforeAdvice = beforeAdvice;
	}

	public AfterAdvice getAfterAdvice() {
		return afterAdvice;
	}

	public void setAfterAdvice(AfterAdvice afterAdvice) {
		this.afterAdvice = afterAdvice;
	}
}
