package com.s8.api.objects.web;


/**
 * 
 * @author pierreconvert
 *
 */
public interface WebS8Session {

	
	/**
	 * 
	 * @param typeName
	 * @param object
	 * @return
	 */
	public WebS8Vertex createVertex(String typeName, WebS8Object object);

}
