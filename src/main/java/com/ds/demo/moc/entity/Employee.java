package com.ds.demo.moc.entity;

/**
 * @author Dai.sha
 * @version V1.0
 * @Title: Employee.java
 * @Package com.ds.demo.moc.entity
 * @Description
 * @date 2019 01-29 16:58.
 */
public class Employee {

	private Integer num;

	private String name;

	private Integer age;

	private float salary;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Employee(Integer num, String name, Integer age, float salary) {
		this.num = num;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee{" +
				"num=" + num +
				", name='" + name + '\'' +
				", age=" + age +
				", salary=" + salary +
				'}';
	}
}
