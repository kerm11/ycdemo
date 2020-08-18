package com.yc.thread.d0808.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;



@Target( ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
}


