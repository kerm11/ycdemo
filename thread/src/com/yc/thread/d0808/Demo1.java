package com.yc.thread.d0808;

import java.util.List;

public class Demo1 {
	
	@Test
	@Select(value = {"select * from a" , "select * from b"})
	public void test() {
		@Test
		int a;
	}
	
	@Select(value = "select * from a" ,age=1)
	public void test1() {
		@Test
		int a;
	}
	

	@Select( "select * from a")
	public void test2() {
		int a;
	}
	
	@Override
	//重写注解 (父类必须有该方法)
	public String toString() {
		@SuppressWarnings("rawtypes")//抑制告警注解
		List a=null;
		System.out.println(a);
		
		return "Demo1 [toString()=" + super.toString() + "]";
	}
	
	
	
}
