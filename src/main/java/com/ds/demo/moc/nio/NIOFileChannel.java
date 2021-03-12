package com.ds.demo.moc.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Administrator
 * @version V1.0
 * @Title: NIOFileChannel.java
 * @Package com.ds.demo.moc.nio
 * @Description
 * @date 2021 02-02 15:45.
 */
public class NIOFileChannel {

	public static void main(String[] args) throws Exception {
	    String str = "Hello, welcome to my world; tt";

	    String filePath = "d:\\file01.txt";
		FileOutputStream fileOutputStream = new FileOutputStream (filePath);

		ByteBuffer byteBuffer = ByteBuffer.allocate (1024);

		FileChannel fileChannel = fileOutputStream.getChannel ();

		byteBuffer.put (str.getBytes ());  //写入buffer
		byteBuffer.flip ();  //反转buffer为读取
		fileChannel.write (byteBuffer); //从buffer写入channel
		fileOutputStream.close ();   //关闭流




		FileInputStream fileInputStream = new FileInputStream (filePath);
		FileChannel inputChannel = fileInputStream.getChannel ();
		inputChannel.read (byteBuffer);

		byteBuffer.flip ();
		/*byte[] bytes = new byte[byteBuffer.limit ()];
		int i= 0;
		while (byteBuffer.hasRemaining ()) {
			bytes[i++] = byteBuffer.get ();
		}*/
		System.out.println(new String (byteBuffer.array ()));
		fileInputStream.close ();
	}
}
