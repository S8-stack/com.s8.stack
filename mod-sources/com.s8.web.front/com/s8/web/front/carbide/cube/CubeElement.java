package com.s8.web.front.carbide.cube;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;

public class CubeElement extends WebS8Object {

	
	public CubeElement(WebS8Session branch, String typeName) {
		super(branch, typeName);
	}

	
	public void setZIndex(int zIndex) {
		vertex.fields().setUInt8Field("zIndex", zIndex);
	}


}
