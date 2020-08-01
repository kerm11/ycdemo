package com.yc.thread.d0725;
/**
 * join
 * @author 邹蔓
 *
 */
public class Demo2 {
	//匿名内部类创建线程
	public static void main(String[] args) {
		Thread t1 = new Thread("线程1") {
			//类定义，匿名方式
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		};
		
		Thread t2 = new Thread("-----------线程2") {
			//类定义，匿名方式
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				
					//t2 线程执行到i=500时，Join到t1中
					try {
						if(i==500) {
							t1.join();
						}
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
				
				}
			}
		};
		
		t1.start();
		t2.start();
		
	}
}
