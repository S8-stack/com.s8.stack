package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;

public class PrimitiveObjFormGetter extends ObjFormElement {

	public PrimitiveObjFormGetter(WebS8Session branch, String typeName) {
		super(branch, typeName);
	}
	
	
	/**
	 * Set field name
	 * @param name
	 */
	public void setFieldName(String name) {
		vertex.fields().setStringUTF8Field("fieldName", name);
	}

}
