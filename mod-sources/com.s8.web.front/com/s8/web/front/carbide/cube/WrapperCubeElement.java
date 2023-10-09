package com.s8.web.front.carbide.cube;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;

public class WrapperCubeElement extends CubeElement {

	
	/**
	 * 
	 * @param branch
	 */
	public WrapperCubeElement(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/cube/WrapperCubeElement");
	}

	
	/**
	 * 
	 * @param content
	 */
	public void setContent(WebS8Object content) {
		vertex.fields().setObjectField("content", content);
	}
	
}
