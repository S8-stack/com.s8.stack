package com.s8.web.front;

/**
 * 
 * @author pierreconvert
 *
 */
public enum S8WebSize {


	INLINED(0x02, "inlined"), 
	
	
	SMALL(0x04, "small"), 
	
	
	NORMAL(0x06, "normal"), 
	
	
	BIG(0x08, "big");
	
	
	/**
	 * 
	 */
	public final int code;
	
	
	/**
	 * 
	 */
	public final String name;
	
	
	
	
	private S8WebSize(int code, String name) { 
		this.code = code; 
		this.name = name;
	}


}
