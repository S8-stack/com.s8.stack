package com.s8.web.front.carbide.navbar;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.web.front.carbide.icons.S8FlatIcon;


/**
 * 
 * @author pierreconvert
 *
 */
public class NavbarMenu extends WebS8Object {

	
	/**
	 * 
	 * @param branch
	 */
	public NavbarMenu(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/navbar/NavbarMenu");
	}
	
	
	public NavbarMenu(WebS8Session branch, S8FlatIcon icon, String name) {
		super(branch, "/s8-web-front/carbide/navbar/NavbarMenu");
		setIcon(icon);
		setName(name);
		onSelected(() -> {
			System.out.println("I'm selected");
		});
	}
	
	
	/**
	 * 
	 * @param menus
	 */
	public void setIcon(S8FlatIcon icon) {
		vertex.fields().setUInt16Field("icon", icon.code);
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		vertex.fields().setStringUTF8Field("name", name);
	}
	
	
	
	public void onSelected(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-selected", lambda);
	}
	
	
	/**
	 * 
	 * @param function
	 */
	public void onSelectedMethod(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-selected", function);
	}
	
}
