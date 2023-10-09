package com.s8.web.front;


/**
 * 
 * @author pierreconvert
 *
 */
public enum S8WebTheme {


	
	/**
	 * 
	 */
	DEFAULT(0x22, "default"),
	

	/**
	 * Default style for light theme
	 */
	LIGHT(0x32, "light"),
	
	
	/**
	 * Default style for light theme
	 */
	DARK(0x36, "dark"),
	
	/**
	 * 
	 */
	PRIMARY(0x42, "primary"), 
	
	
	/**
	 * 
	 */
	OUTLINE(0x62, "outline"), 
	
	
	/**
	 * 
	 */
	WARNING(0xb2, "warning"),
	
	
	/**
	 * 
	 */
	DANGER(0xb4, "danger");
	
	
	
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
	 */
	private S8WebTheme(int code, String name) { 
		this.code = code; 
		this.name = name;
	}

	
}
