package com.s8.api.flow.delivery;


@FunctionalInterface
public interface S8WebResourceGenerator {

	/**
	 * Generate a resource
	 * 
	 * @return
	 */
	public S8WebResource generate() throws Exception;
	
	
}
