package com.yc.thread.d0808.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) {
		
		DemoTest dt = new DemoTest();
		
		Class<?> cls = dt.getClass();
		
		Method beforeMethod = getMethodByAnnotation(cls, Before.class);
		Method afterMethod = getMethodByAnnotation(cls, After.class);

		
		//获取所有方法，并进行遍历
		for(Method m :cls.getMethods()) {
			if(m.getAnnotation(Test.class)!=null) {
				try {
					//动态执行方法，junit 的测试方法不能定义参数
					if(beforeMethod!=null) {
						beforeMethod.invoke(dt);
					}
					m.invoke(dt);
					if(afterMethod!=null) {
						afterMethod.invoke(dt);
					}
				}catch(IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				}catch(InvocationTargetException e) {
					//如果是dt.m 方法出现业务异常
					//将会封装 该异常中
					System.out.println("测试异常，失败！");
					//Throwable t=e.getCause();//异常原因，空指针
	//				System.out.println(t);
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * 扩展1：定义Before 和After
		 * 要求的日志输出结果是：
		 * 				test1
		 * 				测试方法后执行的方法
		 * 				测试方法前执行的方法
		 * 				test2
		 * 				测试方法前执行的方法
		 *  扩展2:统计测试结果 ：成功数量，失败数量
		 */
	
	}
	/**
	 * 根据输入的注解类的类名，返回对应的方法
	 * @param testCls  要查找的被测试的类对象
	 * @param annoCls  要查找方法标注的注解类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getMethodByAnnotation(Class<?> testCls,
			@SuppressWarnings("rawtypes") Class annoCls) {
		for(Method m : testCls.getMethods()) {
			if(m.getAnnotation(annoCls) !=null) {
				return m;
			}
		}
		return null;
	}
}
