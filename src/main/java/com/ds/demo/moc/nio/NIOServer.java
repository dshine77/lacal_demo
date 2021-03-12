package com.ds.demo.moc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @version V1.0
 * @Title: NIOServer.java
 * @Package com.ds.demo.moc.nio
 * @Description
 * @date 2021 02-03 17:37.
 */
public class NIOServer {

	public static void main(String[] args) throws IOException {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open ();
		serverSocketChannel.socket ().bind (new InetSocketAddress (1222));
		serverSocketChannel.configureBlocking (false);

		Selector select = Selector.open ();
		serverSocketChannel.register (select, SelectionKey.OP_ACCEPT);

		
		while (true) {
			if (select.select (1000) == 0) {
				System.out.println("暂时没有客户端连接...");
				continue;
			}

			Set <SelectionKey> keys = select.selectedKeys ();
			Iterator <SelectionKey> iterator = keys.iterator ();
			while (iterator.hasNext ()) {
				SelectionKey key = iterator.next ();
				iterator.remove ();
				if (key.isAcceptable ()) {
					SocketChannel socketChannel = serverSocketChannel.accept ();
					socketChannel.configureBlocking (false);
					socketChannel.register (select, SelectionKey.OP_READ, ByteBuffer.allocate (1024));
					System.out.println("获得了一个连接， socketChannel = " + socketChannel.hashCode ());

				}
				if (key.isReadable ()) {
					SocketChannel channel = (SocketChannel) key.channel ();
					ByteBuffer byteBuffer = (ByteBuffer) key.attachment ();






					channel.read (byteBuffer);
					System.out.println(new String (byteBuffer.array ()));
				}

			}


		}

	}
}
