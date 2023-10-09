package com.s8.web.front;

/**
 * 
 * @author pierreconvert
 *
 */
public enum S8WebDirection {

	/**
	 * 
	 */
	TOP_LEFT(0x22, "top-left"), 
	
	/**
	 * 
	 */
	TOP(0x23, "top"), 
	
	/**
	 * 
	 */
	TOP_RIGHT(0x24, "top-right"),
	
	/**
	 * 
	 */
	RIGHT_TOP(0x32, "right-top"), 
	
	
	/**
	 * 
	 */
	RIGHT(0x33, "right"), 
	
	
	/**
	 * 
	 */
	RIGHT_BOTTOM(0x34, "right-bottom"),
	
	
	/**
	 * 
	 */
	BOTTOM_LEFT(0x42, "bottom-left"), 
	
	/**
	 * 
	 */
	BOTTOM(0x43, "bottom"), 
	
	/**
	 * 
	 */
	BOTTOM_RIGHT(0x44, "bottom-right"),
	
	
	/**
	 * 
	 */
	LEFT_TOP(0x52, "left-top"), 
	
	/**
	 * 
	 */
	LEFT(0x53, "left"), 
	
	
	/**
	 * 
	 */
	LEFT_BOTTOM(0x54, "left-bottom"),

	
	/**
	 * 
	 */
	AUTO(0x64, "auto");
	
	
	
	
	/**
	 * code
	 */
	public final int code;
	

	/**
	 * name
	 */
	public final String name;
	
	/**
	 * 
	 * @param code
	 * @param name
	 */
	private S8WebDirection(int code, String name) { 
		this.code = code; 
		this.name = name;
	}

}
