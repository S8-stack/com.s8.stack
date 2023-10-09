package com.s8.web.front.carbide.objform;


/**
 * 
export const getColor = function (code) {
    switch (code) {
        case 0x00: return "white";
        case 0x01: return "grey";
        case 0x02: return "blue";
        case 0x03: return "purple";
        case 0x04: return "yellow";
        case 0x05: return "green";
        default: throw "Unsupported color code";
    }
}
 * @author pierreconvert
 *
 */
public enum ObjFormColor {


	/**
	 * 
	 */
	White(0x00),
	
	/**
	 * 
	 */
	Grey(0x01),
	
	/**
	 * 
	 */
	Blue(0x02),
	
	/**
	 * 
	 */
	Purple(0x03),
	
	/**
	 * 
	 */
	Yellow(0x04),
    
	/**
	 * 
	 */
	Green(0x05);

    public final int code;
    
    private ObjFormColor(int code){
    	this.code = code;
    }
}
