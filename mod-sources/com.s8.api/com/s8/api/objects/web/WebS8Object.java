package com.s8.api.objects.web;


/**
 * 
 * @author pierreconvert
 *
 */
public class WebS8Object {


	/**
	 * 
	 */
	public final WebS8Vertex vertex;
	
	
	/**
	 * 
	 * @param session
	 * @param typeName
	 */
	public WebS8Object(WebS8Session session, String typeName) {
		super();
		
		/* create vertex and assign object to it */
		vertex = session.createVertex(typeName, this);
	}


}
