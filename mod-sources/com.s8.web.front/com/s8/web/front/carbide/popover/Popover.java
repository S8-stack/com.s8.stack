package com.s8.web.front.carbide.popover;

import java.util.List;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;
import com.s8.web.front.S8WebDirection;
import com.s8.web.front.S8WebTheme;


/**
 * 
 * @author pierreconvert
 *
 */
public class Popover extends WebS8Object {
	
	
	public static Popover create(WebS8Session branch, S8WebTheme theme, S8WebDirection direction) {
		Popover popover = new Popover(branch);
		popover.setDirection(direction);
		popover.setTheme(theme);
		popover.setVisibility(true);
		return popover;
	}

	
	
	
	
	/**
	 * 
	 * @param branch
	 */
	public Popover(WebS8Session session) {
		super(session, "/s8-web-front/carbide/popover/Popover");
	}
	
	

	/**
	 * 
	 * @param direction
	 */
	public void setVisibility(boolean isVisible) {
		vertex.fields().setBool8Field("visibility", isVisible);
	}
	
	
	/**
	 * 
	 * @param direction
	 */
	public void setDirection(S8WebDirection direction) {
		vertex.fields().setUInt8Field("direction", direction.code);
	}
	
	
	/**
	 * 
	 * @param theme
	 */
	public void setTheme(S8WebTheme theme) {
		vertex.fields().setUInt8Field("theme", theme.code);
	}
	
	
	/**
	 * 
	 * @param elements
	 */
	public void setElements(List<HTML_NeNode> elements) {
		vertex.fields().setObjectListField("content", elements);
	}
	
	/**
	 * 
	 * @param elements
	 */
	public void setElements(HTML_NeNode... elements) {
		vertex.fields().setObjectListField("content", elements);
	}
	
	

	/**
	 * 
	 * @param elements
	 */
	public void setPopover(Popover popover) {
		vertex.fields().setObjectField("popover", popover);
	}
	
	
	/**
	 * 
	 */
	public void removePopover() {
		vertex.fields().setObjectField("popover", null);
	}
	
	
	
}
