package com.s8.web.front.websvg;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;

public class WebSVG_Element extends WebS8Object {

	public WebSVG_Element(WebS8Session branch, String typeName) {
		super(branch, typeName);
	}
	
	
	public void isBoundingBoxRelevant(boolean state) {
		vertex.fields().setBool8Field("isBoundingBoxRelevant", state);
	}
	
	
	/**
	 * 
	 * @param solidity
	 */
	public void setStrokeSolidity(WebSVG_StrokeSolidity solidity) {
		vertex.fields().setUInt8Field("strokeSolidity", solidity.code);
	}
	
	
	/**
	 * 
	 * @param color
	 */
	public void setStrokeColor(WebSVG_StrokeColor color) {
		vertex.fields().setUInt8Field("strokeColor", color.code);
	}
	
	
	/**
	 * Style: 
	 * @param thickness
	 */
	public void setStrokeThickness(float thickness) {
		vertex.fields().setFloat32Field("strokeThickness", thickness);
	}

}
