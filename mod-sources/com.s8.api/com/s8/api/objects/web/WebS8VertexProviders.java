package com.s8.api.objects.web;

import com.s8.api.flow.delivery.S8WebResourceGenerator;

public interface WebS8VertexProviders {



	/**
	 * 
	 * @param ordinal
	 * @return
	 */
	public S8WebResourceGenerator getGenerator(int ordinal);
	

	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setRawProvider(String name, S8WebResourceGenerator generator);

	
}
