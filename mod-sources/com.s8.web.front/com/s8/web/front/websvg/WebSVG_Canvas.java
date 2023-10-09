package com.s8.web.front.websvg;

import java.util.List;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Canvas extends HTML_NeNode {

	
	/**
	 * 
	 * @param branch
	 * @param typeName
	 */
	public WebSVG_Canvas(WebS8Session branch) {
		super(branch, "/s8-web-front/websvg/WebSVG_Canvas");
	}
	
	
	/**
	 * 
	 * @param elements
	 */
	public void addElement(WebSVG_Element element) {
		vertex.fields().addObjToList("elements", element);
	}


	public void setElements(List<WebSVG_Element> elements) {
		vertex.fields().setObjectListField("elements", elements);
	}
	

}
