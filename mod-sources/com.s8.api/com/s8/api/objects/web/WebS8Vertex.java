package com.s8.api.objects.web;

import java.io.IOException;

import com.s8.api.bytes.ByteOutflow;


/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public interface WebS8Vertex {

	
	/**
	 * 
	 * @return index
	 */
	public String getId();
	
	
	
	public WebS8Session getSession();
	
	
	/**
	 * Object attached to this vertex
	 * @return
	 */
	public WebS8Object getAttachedObject();



	public void expose(int slot);




	public void publish(ByteOutflow outflow) throws IOException;
	



	/**
	 * 
	 * @return the vertex fields handling module
	 */
	public WebS8VertexFields fields();
	

	/**
	 * 
	 * @return the vertex methods handling module
	 */
	public WebS8VertexMethods methods();
	
	
	/**
	 * 
	 * @return the vertex generators handling module
	 */
	public WebS8VertexProviders providers();


}
