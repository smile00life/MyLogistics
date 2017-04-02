package com.logistics.annotation;

import java.lang.annotation.*;
	/**  
	 *自定义注解 拦截service  本项目中未用到
	 */    
	    
	@Target({ElementType.PARAMETER, ElementType.METHOD})    
	@Retention(RetentionPolicy.RUNTIME)    
	@Documented    
	public  @interface SystemServiceLog {    
	    
	    String description()  default "";    
	    
	    
	}    

