package com.s8.api.flow.delivery;



public class S8WebResource {

	
	/**
	 * To be re-interpreted as a MIME type
	 */
	public final String type;
	
	
	/**
	 * data
	 */
	public final byte[] data;

	
	/**
	 * 
	 * @param type
	 * @param data
	 */
	public S8WebResource(String type, byte[] data) {
		super();
		this.type = type;
		this.data = data;
	}
	
	
}
