package com.s8.io.bohr.neon.core;

import com.s8.api.flow.delivery.S8WebResourceGenerator;
import com.s8.api.objects.web.WebS8VertexProviders;
import com.s8.io.bohr.neon.providers.NeProvider;
import com.s8.io.bohr.neon.providers.RawNeProvider;


/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeVertexProviders0 implements WebS8VertexProviders {


	public final NeVertex0 vertex;
	
	public final NeObjectTypeProviders prototype;


	private S8WebResourceGenerator[] generators;

	
	


	/**
	 * 
	 * @param branch
	 * @param typeName
	 * @param object
	 */
	public NeVertexProviders0(NeVertex0 vertex, NeObjectTypeProviders prototype) {
		super();	
		this.vertex = vertex;
		this.prototype = prototype;
		
		generators = new S8WebResourceGenerator[4];
	}
	




	/**
	 * 
	 * @param method
	 * @return
	 */
	private int getProviderOrdinal(NeProvider method) {
		int ordinal = method.ordinal;
		while(ordinal >= generators.length) {
			int n = generators.length;
			S8WebResourceGenerator[] extendedGenerators = new S8WebResourceGenerator[2 * n];
			for(int i = 0; i < n; i++) { extendedGenerators[i] = generators[i]; }
			generators = extendedGenerators;
		}
		return ordinal;
	}
	
	
	



	@Override
	public S8WebResourceGenerator getGenerator(int ordinal) {
		return generators[ordinal];
	}
	
	
	

	@Override
	public void setRawProvider(String name, S8WebResourceGenerator generator) {
		/* retrieve (or define if first time) method runner */
		RawNeProvider method = prototype.getRawProvider(name);
		int ordinal = getProviderOrdinal(method);
		generators[ordinal] = generator;
	}
	
}
