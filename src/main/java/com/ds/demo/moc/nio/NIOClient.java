package com.ds.demo.moc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * @version V1.0
 * @Title: NIOClient.java
 * @Package com.ds.demo.moc.nio
 * @Description
 * @date 2021 02-03 17:51.
 */
public class NIOClient {

	public static void main(String[] args) throws IOException {

		SocketChannel socketChannel = SocketChannel.open ();
		socketChannel.configureBlocking (false);

		InetSocketAddress inetSocketAddress = new InetSocketAddress ("127.0.0.1", 1222);

		if (!socketChannel.connect (inetSocketAddress)) {
			while (!socketChannel.finishConnect ()) {
				System.out.println("连接中....");
			}

			String msg = "hello, good night.";

			ByteBuffer byteBuffer = ByteBuffer.wrap (msg.getBytes ());

			socketChannel.write (byteBuffer);
			System.in.read ();
		}

	}
}
