package com.yc.thread.d0726;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo01 {
	
	//https://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip
	
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		URLConnection conn = url.openConnection();
		
		//获取文件总大小
		int filesize = conn.getContentLength();
		//每块的大小（自定义2M）
		int blocksize = 2*1024*1024;
		//计算块数
		int blocknums  =filesize/blocksize;
		
		if( filesize%blocksize !=0) {
			blocknums++;
		}
		
		System.out.println("开始下载");
		
		//分块下载
		for(int i=0;i<blocknums;i++) {
			System.out.println("第"+(i+1)+"块开始下载");
			/**在没次循环中 获取一个连接对象  */
			conn = url.openConnection();
			InputStream in=conn.getInputStream();
			FileOutputStream out = new FileOutputStream("e:/apache-tomcat-10.0.0-M7-windows-x64.zip");
		
			//开始的字节数
			int beginBytes = i*blocksize;
			//结束的字节数
			int endBytes = beginBytes+blocksize;
			//结束的字节数不能超过文件的大小
			if(endBytes>filesize) {
				endBytes=filesize;
			}
		
			//跳过开始的字节数
			in.skip(beginBytes);
			
			/**
			 * 请下载当前块内的字节数
			 * 1.计数
			 * 2.判断
			 */
			//当前下载到的位置
			int position = beginBytes;
			
			byte[] buffer = new byte[1024];
			int count;
			while((count = in.read(buffer))>0) {
				if( position + count >endBytes) {
					//计算超出部分
					int a =position+count -endBytes;
					//减出超出的部分
					count = count -a;
				}
				out.write(buffer,0,count);
				//更新下载位置（向前推进）
				position+=count;
				//如果下载位置已经到达该块结束位置
				if(position>=endBytes) {
					break;
				}
			}
			in.close();
			out.close();
			System.out.println("第"+(i+1)+"块结束下载");
		}
		
		System.out.println("下载完成");
	}

}
