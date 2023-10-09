package com.s8.web.front.carbide.dock;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;

/*
 * 
 */
public class DockItem extends WebS8Object {
	
	
	public static DockItem create(WebS8Session branch, String name, String iconFilename, VoidNeFunction function) {
		DockItem dockItem = new DockItem(branch, name, iconFilename);
		dockItem.onClick(function);
		return dockItem;
	}
	
	public static DockItem createLambda(WebS8Session branch, String name, String iconFilename, VoidLambda lambda) {
		DockItem dockItem = new DockItem(branch, name, iconFilename);
		dockItem.onClickLambda(lambda);
		return dockItem;
	}
	
	
	public String name;
	
	public String iconFilename;
	
	/**
	 * 
	 * @param branch
	 * @param name
	 * @param iconFilename
	 */
	public DockItem(WebS8Session branch, String name, String iconFilename) {
		super(branch, "/s8-web-front/carbide/dock/DockItem");
		vertex.fields().setStringUTF8Field("name", name);
		vertex.fields().setStringUTF8Field("iconFilename", iconFilename);
		
		vertex.methods().setBool8Method("onClickSelect", (f,v) -> { System.out.print("hello world"); });
		
	}
	
	
	/**
	 * 
	 * @param function
	 */
	public void onClick(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-click", function);
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onClickLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-click", lambda);
	}
	
	
}
