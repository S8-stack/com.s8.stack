package com.s8.api.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



/**
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class S8BuildException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3056476425696740047L;

	private Class<?> type;
	
	private Field field;
	
	private Method method;
	
	
	public S8BuildException(String message) {
		super(message);
	}
	
	
	/**
	 * 
	 * @param message
	 * @param type
	 */
	public S8BuildException(String message, Class<?> type) {
		super(message+"for type: "+type);
		this.type = type;
	}

	public S8BuildException(String message, Field field) {
		super(message+"for type: "+field);
		this.field = field;
		this.type = field.getDeclaringClass();
	}
	
	
	public S8BuildException(String message, Method method) {
		super(message+", for method: "+method);
		this.method = method;
		this.type = method.getDeclaringClass();
	}
	
	
	
	public Class<?> getCauseType(){
		return type;
	}
	
	public Field getCauseField(){
		return field;
	}
	
	public Method getCauseMethod(){
		return method;
	}
	
	
}
