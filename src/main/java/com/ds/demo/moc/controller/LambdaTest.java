package com.ds.demo.moc.controller;

import com.ds.demo.moc.entity.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Dai.sha
 * @version V1.0
 * @Title: LambdaTest.java
 * @Package com.ds.demo.moc.controller
 * @Description
 * @date 2019 01-29 16:04.
 */
public class LambdaTest {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(
				new Employee(101,"张三",18,9999.99f),
				new Employee(102,"李四",59,666.66f),
				new Employee(103,"王五",28,6333.66f),
				new Employee(104,"赵柳",30,7566.66f),
				new Employee(105,"田七",32,10066.66f)
		);

		Collections.sort(list,(x,y) -> {
			if (x.getAge().intValue() == y.getAge().intValue()){
				return x.getName().compareTo(y.getName());
			}else{
				return Integer.compare(x.getAge(), y.getAge());
			}
		});

		list.forEach(e -> System.out.println(e));

	}
}
