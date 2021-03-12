package com.ds.demo.moc.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Dai.sha
 * @version V1.0
 * @Title: JsoupParser.java
 * @Package com.ds.demo.moc.controller
 * @Description
 * @date 2018 12-04 11:38.
 */
public class JsoupParser {


	private static OutputStream os;

	public static void main(String[] args) {
		try {
		Document doc = Jsoup.connect("https://www.csdn.net/").get();
		System.out.println(doc.title());
		//把文章标题和连接写入txt文件
		Element feedlist_id = doc.getElementById("feedlist_id");
		Elements li = feedlist_id.select("li.csdn-tracking-statistics");
		Elements a = li.select("a");
		//指定文件名及路径
		File file = new File("D:\\output\\text1.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		//写入本地
		PrintWriter pw = new PrintWriter("D:\\output\\text1.txt","UTF-8");
		for (Element element : a) {
			pw.println(element.text());
			pw.println(element.attr("href"));
			pw.println("------------------------------------------------------------------------------------------------------------------------------------");
		}
		pw.close(); //关闭输出流
		//获取页面上的图片保存到本地
		Elements imgs = doc.select("img[src$=.png]");
		for (Element element : imgs) {
			String img = element.attr("src");
			String url = "";
			if (img.indexOf("http") == -1){
				url = "https:"+img;
			}else {
				url = img;
			}

			System.out.println(url);
			System.out.println(url.indexOf("csdn"));
			if (url.indexOf("csdn")==-1) {
				continue;
			}
			URL u = new URL(url);
			URLConnection uc=u.openConnection();
			//获取数据流
			InputStream is=uc.getInputStream();
			//获取后缀名
			String imageName = img.substring(img.lastIndexOf("/") + 1,img.length());
			//写入本地
			os = new FileOutputStream(new File("D:\\output\\img", imageName));
			byte[] b = new byte[1024];
			int i=0;
			while((i=is.read(b))!=-1){
				os.write(b, 0, i);
			}
			is.close();
			os.close();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	}



