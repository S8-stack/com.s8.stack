package com.s8.web.front.carbide.structure;

import java.util.List;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class Row extends HTML_NeNode {
	
	public static Row create(WebS8Session branch, HTML_NeNode... nodes) {
		Row title = new Row(branch);
		title.setContent(nodes);
		return title;
	}

	
	/**
	 * 
	 * @param branch
	 */
	public Row(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/structure/Row");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setContent(List<HTML_NeNode> nodes) {
		vertex.fields().setObjectListField("content", nodes);
	}
	
	/**
	 * 
	 * @param nodes
	 */
	public void setContent(HTML_NeNode... nodes) {
		vertex.fields().setObjectListField("content", nodes);
	}
}
