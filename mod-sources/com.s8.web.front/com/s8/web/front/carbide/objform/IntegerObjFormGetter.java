package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;

public class IntegerObjFormGetter extends PrimitiveObjFormGetter {

	public IntegerObjFormGetter(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/IntegerObjFormGetter");
	}
	
	
	/**
	 * Set field name
	 * @param name
	 */
	public void setValue(int value) {
		vertex.fields().setInt32Field("value", value);
	}
	

}
