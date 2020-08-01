package com.yc.thread.d0727;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
	public static void main(String[] args) throws IOException {
		
		ServerSocket tomcat=new ServerSocket(8080);
		System.out.println("tomcat 服务器启动完成，监听端口：8080");
		
		boolean running = true;
		while(running) {
			Socket socket =tomcat.accept();
			new Thread() {
				public void run() {
					try {
						System.out.println("接收到请求");
						InputStream in=socket.getInputStream();
						OutputStream out=socket.getOutputStream();
						byte[] buffer = new byte[1024];
						int count;
						count = in.read(buffer);
						if( count>0) {
							System.out.println( new String(buffer,0,count));
							
							
							//响应头行
							out.write("HTTP/1.1 200 OK\n".getBytes());
							//响应头域
							out.write("ContentType: text/html; charset=UTF-8\"\n".getBytes());
							//空行 CRLF
							out.write("\n".getBytes());
							String path="F:\\software\\apache-tomcat-8.5.54\\webapps\\photo/helloworld.html";
							FileInputStream fis = new FileInputStream(path);
							while( (count = fis.read(buffer))>0) {
								out.write(buffer,0,count);
							}
							/**
							 * 问题：
							 * 1.只能回复一次
							 * 2.回复的内容永远不变
							 * 	  1.解析出请求行中的资源名 /photo/new.html
							 * 	  2.读取文件内容输出到实体中
							 */
							fis.close();
						}
				
						//实体
					//	out.write("<h1>Hello World</h1>".getBytes());
						
						//out.write("  <a  href='https://www.taobao.com/'>Hello World</a>  ".getBytes());
						socket.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
		//Unreachable code 代码不可达
		tomcat.close();
		
	}


}
