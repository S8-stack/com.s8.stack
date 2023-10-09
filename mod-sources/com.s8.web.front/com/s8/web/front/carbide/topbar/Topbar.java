package com.s8.web.front.carbide.topbar;

import java.util.ArrayList;
import java.util.List;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class Topbar extends HTML_NeNode {

	
	/**
	 * 
	 * @param branch
	 */
	public Topbar(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/topbar/Topbar");
	}
	
	
	/**
	 * 
	 * @param menus
	 */
	public void setElements(List<TopbarElement> menus) {
		vertex.fields().setObjectListField("elements", menus);
	}
	

	/**
	 * 
	 * @param menus
	 */
	public void setElements(TopbarElement... elements) {
		int n = elements.length;
		List<TopbarElement> list = new ArrayList<>(n);
		for(int i =0; i<n; i++) { list.add(elements[i]); }
		vertex.fields().setObjectListField("elements", list);
	}
	
	
	
}
