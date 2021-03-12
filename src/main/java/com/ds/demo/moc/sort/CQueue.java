package com.ds.demo.moc.sort;

import java.util.Stack;

/**
 * @author Administrator
 * @version V1.0
 * @Title: Cqueue.java
 * @Package com.ds.demo.moc.sort
 * @Description
 * @date 2021 01-21 18:45.
 */
public class CQueue {

	Stack<Integer> stack1 = null;
	Stack<Integer> stack2 = null;

	public CQueue() {
		stack1 = new Stack <> ();
		stack2 = new Stack <> ();
	}

	public void appendTail(int value) {
		stack1.push (value);
	}

	public int deleteHead() {
		if (!stack2.isEmpty ()) {
			return stack2.pop ();
		}
		while (!stack1.isEmpty ()) {
			stack2.push (stack1.pop ());
		}

		return !stack2.isEmpty () ? stack2.pop () : -1;
	}
}