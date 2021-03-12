package com.ds.demo.moc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @version V1.0
 * @Title: GroupChartServer.java
 * @Package com.ds.demo.moc.nio
 * @Description  群聊服务端
 * @date 2021 02-04 15:54.
 */
public class GroupChartServer {

	private static Integer PORT = 1222;
	private ServerSocketChannel listenChannel;
	private Selector selector;

	/**
	 * 构造器，初始化工作
	 * @throws Exception
	 */
	public GroupChartServer() {

		try {
			//1.得到选择器
			selector = Selector.open ();
			//2.得到channel
			listenChannel = ServerSocketChannel.open ();
			//2.1设置非阻塞模式
			listenChannel.configureBlocking (false);
			//3.将通道绑定到对应的端口
			listenChannel.socket ().bind (new InetSocketAddress (PORT));
			//4.将通道注册到选择器上
			listenChannel.register (selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务器已启动，等待客户端连接...");
		} catch (Exception e) {

		}


	}

	/**
	 * 监听客户端的连接
	 */
	public void listen(){

		while (true) {
			try {
				int count = selector.select ();
				if (count > 0) {
					Set <SelectionKey> selectionKeys = selector.selectedKeys ();
					Iterator <SelectionKey> iterator = selectionKeys.iterator ();
					while (iterator.hasNext ()) {  //有事件需要处理
						//遍历需要处理的事件集合
						SelectionKey key = iterator.next ();
						//监听到accept事件
						if (key.isAcceptable ()) {
							SocketChannel clientChannel = listenChannel.accept ();
							clientChannel.configureBlocking (false);
							clientChannel.register (selector, SelectionKey.OP_READ);
							System.out.println(clientChannel.getRemoteAddress () + " 上线了...");
						}
						if (key.isReadable ()) {
							//处理读取信息
							readData(key);
						}
						iterator.remove ();
					}

				}

			} catch (IOException e) {
				e.printStackTrace ();
			} finally {
				//发生异常的处理
			}


		}

	}

	private void readData(SelectionKey key) {
		SocketChannel socketChannel = null;

		try {
			//得到channel
			socketChannel = (SocketChannel) key.channel ();
			//创建buffer
			ByteBuffer byteBuffer = ByteBuffer.allocate (1024);
			int readCount = socketChannel.read (byteBuffer);
			if (readCount > 0) {
				String msg = new String (byteBuffer.array ());
				//收到客户端的消息并转发给除自己外的其它客户端
				System.out.println("收到来自" + socketChannel.getRemoteAddress () + ":  " + msg.trim ());
				forwardMsg(msg, socketChannel);
			}

		} catch (Exception e) {
			try {
				System.out.println(socketChannel.getRemoteAddress () + "， 离线了");
				//取消注册
				key.cancel ();
				//关闭通道
				socketChannel.close ();
			} catch (Exception e2) {
				e2.printStackTrace ();
			}
		}
	}

	/**
	 * 转发消息
	 * @param msg
	 * @param self
	 * @throws IOException
	 */
	private void forwardMsg(String msg, SocketChannel self) throws IOException {
		System.out.println("转发中...");
		//获取到所有连接的客户端
		Set <SelectionKey> keys = selector.keys ();
		if (keys != null && keys.size () > 0) {
			for (SelectionKey clientKey : keys) {
				Channel channel = clientKey.channel ();
				//如果是自己不需要转发
				if (channel instanceof  SocketChannel && channel != self) {
					SocketChannel clientChannel = (SocketChannel) channel;
					clientChannel.write (ByteBuffer.wrap (msg.getBytes ()));
				}
			}
		}
	}

	public static void main(String[] args) {
		GroupChartServer groupChartServer = new GroupChartServer ();
		groupChartServer.listen ();
	}


}
