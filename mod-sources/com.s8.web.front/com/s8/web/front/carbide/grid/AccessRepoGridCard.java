package com.s8.web.front.carbide.grid;

import com.s8.api.objects.web.WebS8Session;


/**
 * 
 * 
 * 
 * @author pierreconvert
 *
 */
public class AccessRepoGridCard extends GridCard {

	
	
	/**
	 * 
	 * @param branch
	 */
	public AccessRepoGridCard(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/grid/AccessRepoGridCard");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void setTitle(String name) {
		vertex.fields().setStringUTF8Field("title", name);
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setInfo(String name) {
		vertex.fields().setStringUTF8Field("info", name);
	}
	
	
}
