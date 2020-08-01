package com.yc.thread.d0725;

public class Test01 {

	public static void main(String[] args) {
		A a = new A();
		new Thread(a).start();
		int j = a.i; // 第五行
		System.out.println("j=" + j); // 第六行  j=1
	}	
}
class A implements Runnable {
	public int i = 1;

	public void run() {
		this.i = 10;
	}
}