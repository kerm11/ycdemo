package com.yc.thread.d0808;

import java.lang.reflect.Method;

@Test("测试类")
public class Demo2 {
	@Test("测试方法")
	public void test() {
		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Demo2 d= new Demo2();
		//java黑科技---反射
		//获取一个类的类对象（泛型对象）
		//方式一：类名+class关键字
		Class<?> cls = Demo2.class;
		
		//方式二：对象名+getclass()
		cls=d.getClass();
		
		//方式三：Class类+forName方法（加载时会执行静态块，完成初始化）
		cls = Class.forName("com.yc.thread.d0808.Demo2");
		
		//反射操作
		cls.getFields(); // 获取公有的属性数组
		cls.getMethods() ; // 获取公有的方法数组
		cls.getConstructors();// 获取构造方法数组
		//...
		
		cls.getAnnotations();//// 获取类上注解数组
		Test test1 =cls.getAnnotation(Test.class); //根据注解类的类对象获取注解对象
		//如果获取为空，表示没有找到对应的注解
		System.out.println(test1.value());
		//获取方法对象
		Method m =cls.getMethod("test");
		
		Test test2 =cls.getAnnotation(Test.class); 
		System.out.println(test2.value());
	}
}
