package com.s8.web.front.carbide.structure;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class InlineSpacer extends HTML_NeNode {
	
	/**
	 * 
	 * @param branch
	 * @param width
	 * @return
	 */
	public static InlineSpacer create(WebS8Session branch, double width) {
		InlineSpacer title = new InlineSpacer(branch);
		title.setWidth(width);
		return title;
	}

	
	/**
	 * 
	 * @param branch
	 */
	public InlineSpacer(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/structure/InlineSpacer");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setWidth(double width) {
		vertex.fields().setFloat32Field("width", (float) width);
	}
	
}
