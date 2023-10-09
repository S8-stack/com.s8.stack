package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;

public class PrimitiveObjFormSetter extends ObjFormElement {

	public PrimitiveObjFormSetter(WebS8Session branch, String typeName) {
		super(branch, typeName);
	}
	
	
	/**
	 * Set field name
	 * @param name
	 */
	public void setName(String name) {
		vertex.fields().setStringUTF8Field("fieldName", name);
	}

}
