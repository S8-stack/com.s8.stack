package com.s8.web.front.websvg;

/**
 * 
 * @author pierreconvert
 *
 */
public enum WebSVG_StrokeColor {

	BLACK(0x02, "black"),
	
	GREY(0x03, "grey"),
	
	RED(0x04, "red"),
	
	GREEN(0x05, "green"),
	
	BLUE(0x06, "blue");
	
	
	
	
	
	
	/**
	 * code : WebFront
	 */
	public final int code;
	
	
	/**
	 * 
	 */
	public final String name;

	
	private WebSVG_StrokeColor(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
}
