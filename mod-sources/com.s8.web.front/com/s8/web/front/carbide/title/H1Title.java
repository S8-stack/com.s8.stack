package com.s8.web.front.carbide.title;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class H1Title extends HTML_NeNode {
	
	public static H1Title create(WebS8Session branch, String text) {
		H1Title title = new H1Title(branch);
		title.setText(text);
		return title;
	}

	
	/**
	 * 
	 * @param branch
	 */
	public H1Title(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/title/H1Title");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setText(String text) {
		vertex.fields().setStringUTF8Field("text", text);
	}
}
