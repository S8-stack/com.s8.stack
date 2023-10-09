package com.s8.web.front.carbide.button;

import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.web.front.HTML_NeNode;
import com.s8.web.front.S8WebSize;
import com.s8.web.front.S8WebTheme;


/**
 * 
 * @author pierreconvert
 *
 */
public class Button extends HTML_NeNode {
	
	/**
	 * 
	 * @param branch
	 * @param label
	 * @param size
	 * @param style
	 * @return
	 */
	public static Button create(WebS8Session branch, String label, S8WebSize size, S8WebTheme theme) {
		Button button = new Button(branch);
		
		button.setLabel(label);
		button.setSize(size);
		button.setTheme(theme);
		
		return button;
	}
	
	public enum Style{
		DEFAULT(0x02), PRIMARY(0x03), OUTLINE(0x04), DANGER(0x05);
		public int code;
		private Style(int code) { this.code = code; }
	}
	
	
	/**
	 * 
	 * @param branch
	 */
	public Button(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/button/Button");
		
		/* to be overridden */
		vertex.methods().setVoidMethodLambda("on-click", () -> System.out.println("I'm clicked!"));
	}
	
	
	/**
	 * 
	 * @param text
	 */
	public void setLabel(String text) {
		vertex.fields().setStringUTF8Field("label", text);
	}
	
	
	

	/**
	 * 
	 * @param menus
	 */
	public void setSize(S8WebSize size) {
		vertex.fields().setUInt8Field("size", size.code);
	}
	
	
	/**
	 * 
	 * @param menus
	 */
	public void setTheme(S8WebTheme theme) {
		vertex.fields().setUInt8Field("theme", theme.code);
	}
	
	
	/**
	 * 
	 * @param state
	 */
	public void setEnabled(boolean state) {
		vertex.fields().setBool8Field("isEnabled", state);
	}
	
	
	
	public void onClick(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-click", function);
	}
	
	
	public void onClickLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-click", lambda);
	}
	
}

