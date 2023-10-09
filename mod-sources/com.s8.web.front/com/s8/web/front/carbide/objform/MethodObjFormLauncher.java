package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;

public class MethodObjFormLauncher extends ObjFormElement {

	public MethodObjFormLauncher(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/MethodObjFormLauncher");
	}
	
	public void setName(String name) {
		vertex.fields().setStringUTF8Field("name", name);
	}

}
