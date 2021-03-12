package com.ds.demo.moc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version V1.0
 * @Title: GroupChartClient.java
 * @Package com.ds.demo.moc.nio
 * @Description 群聊客户端
 * @date 2021 02-04 16:57.
 */
public class GroupChartClient {

	private final String HOST = "127.0.0.1";
	private final Integer PORT = 1222;
	private SocketChannel socketChannel;
	private Selector selector;
	private String userName;

	public GroupChartClient() {

		//初始化客户端
		try {

			selector = Selector.open ();

			socketChannel = SocketChannel.open (new InetSocketAddress (HOST, PORT));
			socketChannel.configureBlocking (false);

			socketChannel.register (selector, SelectionKey.OP_READ);

			userName = socketChannel.getLocalAddress ().toString ();

			System.out.println (userName + " is ok, please send info");


		} catch (Exception e) {

		}
	}

	/**
	 * 读取消息
	 */
	public void readInfo() {

		try {
			int readChannel = selector.select ();
			if (readChannel > 0) {
				Set <SelectionKey> selectionKeys = selector.selectedKeys ();
				Iterator iterator = selectionKeys.iterator ();
				while (iterator.hasNext ()) {
					SelectionKey key = (SelectionKey) iterator.next ();
					if (key.isReadable ()) {
						SocketChannel channel = (SocketChannel) key.channel ();
						ByteBuffer byteBuffer = ByteBuffer.allocate (1024);

						channel.read (byteBuffer);
						String string = new String (byteBuffer.array ());
						System.out.println (string.trim ());
					}
					iterator.remove ();
				}
			}
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	public void sendInfo(String msg) {

		msg = userName + " 说： " + msg;
		try {
			socketChannel.write (ByteBuffer.wrap (msg.getBytes ()));
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}

	public static void main(String[] args) {
	    GroupChartClient client = new GroupChartClient ();

	    new Thread (() -> {
		    while (true) {
			    client.readInfo ();
			    try {
				    TimeUnit.SECONDS.sleep (3);
			    } catch (InterruptedException e) {
				    e.printStackTrace ();
			    }
		    }
	    }).start ();


		Scanner scanner = new Scanner (System.in);
		while (scanner.hasNextLine ()) {
			String str = scanner.nextLine ();
			client.sendInfo (str);
		}

	}

}
