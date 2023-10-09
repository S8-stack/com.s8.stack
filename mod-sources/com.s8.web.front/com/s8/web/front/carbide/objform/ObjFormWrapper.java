package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class ObjFormWrapper extends HTML_NeNode {

	
	
	/**
	 * 
	 */
	public ObjFormWrapper(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/ObjFormWrapper");
	}
	
	
	/**
	 * 
	 * @param element
	 */
	public void setRoot(ObjFormElement element) {
		vertex.fields().setObjectField("root", element);
	}
	
	
	
}
