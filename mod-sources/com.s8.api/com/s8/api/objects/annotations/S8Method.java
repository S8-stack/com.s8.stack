package com.s8.api.objects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author pierreconvert
 *
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface S8Method {

	
	/**
	 * 
	 * @return the name this field will be bound to
	 */
	public String name();
	
	
	/**
	 * 
	 * @return the chosen I/O format for exchanging data
	 */
	public String flow() default "(default)";
	
	
	/**
	 * 
	 * @return a Bool64 
	 */
	public long props() default 0L;
	
	public long mask() default 0x00L;

}
