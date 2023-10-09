package com.s8.web.front;


/**
 * 
 * @author pierreconvert
 * 
 *  case 0x02: return "ok";
        case 0x06: return "out-of-sync";
        case 0x13: return "warning";
        case 0x26: return "error";
 *
 */
public enum S8WebStatus {


	/**
	 * 
	 */
	DISABLED(0x00, "Disabled for this location"),
	
	
	/**
	 * 
	 */
	OK(0x02, "ok"),
	

	/**
	 * Default style for light theme
	 */
	OUT_OF_SYNC(0x06, "out-of-sync"),
	
	
	/**
	 * Default style for light theme
	 */
	WARNING(0x13, "warnng"),
	
	/**
	 * 
	 */
	ERROR(0x26, "error");
	
	
	
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
	private S8WebStatus(int code, String name) { 
		this.code = code; 
		this.name = name;
	}

	
}
