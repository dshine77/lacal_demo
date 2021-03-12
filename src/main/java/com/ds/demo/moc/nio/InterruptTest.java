package com.ds.demo.moc.nio;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version V1.0
 * @Title: InterruptTest.java
 * @Package com.ds.demo.moc.nio
 * @Description
 * @date 2021 02-27 12:16.
 */
public class InterruptTest {

	ArrayList arrayList = new ArrayList ();

	public static void main(String[] args) {
	    Thread thread = new Thread (new Runnable () {
		    @Override
		    public void run() {
		    	synchronized (this)  {
				    try {
					    wait ();
				    } catch (InterruptedException e) {
					    System.out.println( "InterruptedException, " + Thread.currentThread ().isInterrupted ());
				    }
			    }

		    }
	    });

	    thread.start ();

	    boolean interrupt = thread.isInterrupted ();
	    System.out.println("interrupt = " + interrupt);

	    thread.interrupt ();
		try {
			Thread.sleep (2000);
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}


		boolean interrupt2 = thread.isInterrupted ();
		System.out.println("interrupt1 = " + interrupt2);

		boolean interrupt3 = Thread.interrupted ();
		System.out.println("interrupt3 = " + interrupt3);

	}
}
