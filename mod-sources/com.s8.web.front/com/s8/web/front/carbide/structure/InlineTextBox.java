package com.s8.web.front.carbide.structure;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class InlineTextBox extends HTML_NeNode {
	
	/**
	 * 
	 * @param branch
	 * @param width
	 * @return
	 */
	public static InlineTextBox create(WebS8Session branch, String text) {
		InlineTextBox title = new InlineTextBox(branch);
		title.setText(text);
		return title;
	}

	
	/**
	 * 
	 * @param branch
	 */
	public InlineTextBox(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/structure/InlineTextBox");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setText(String text) {
		vertex.fields().setStringUTF8Field("text", text);
	}
	
}
