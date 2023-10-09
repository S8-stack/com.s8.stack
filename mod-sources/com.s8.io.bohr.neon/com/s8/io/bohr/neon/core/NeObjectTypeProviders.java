package com.s8.io.bohr.neon.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.s8.api.bytes.ByteInflow;
import com.s8.io.bohr.neon.providers.NeProvider;
import com.s8.io.bohr.neon.providers.RawNeProvider;



/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeObjectTypeProviders {


	/**
	 * The name associated to this object type
	 */
	public final NeObjectTypeHandler prototype;

	/**
	 * Code-based. Code is defined by inbound
	 */
	private NeProvider[] providers;

	private Map<String, NeProvider> providersByName;

	private int nextProviderOrdinal = 0;

	
	
	public NeObjectTypeProviders(NeObjectTypeHandler prototype) {
		super();
		
		this.prototype = prototype;

		this.providers = new NeProvider[2];
		this.providersByName = new HashMap<>();
	}




	public final static String RUNTIME_MODFICATION_ERROR_MESSAGE = "Prototype can only be edited at compile time";


	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public NeProvider getProvider(int code) {
		return providers[code];
	}
	
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public RawNeProvider getRawProvider(String name) {
		NeProvider provider = providersByName.get(name);
		if(provider != null) {
			return (RawNeProvider) provider;
		}
		else {
			RawNeProvider newProvider = new RawNeProvider(this, name, nextProviderOrdinal++);
			appendProvider(newProvider);
			return newProvider;
		}
	}
	






	/**
	 * Assign ordinal
	 * @param field
	 */
	private void appendProvider(NeProvider methodRunner) {
		
		String name = methodRunner.name;
		
		if(providersByName.containsKey(name)) {
			System.err.println("NE_COMPILE_ERROR: METHOD name conflict: "+name);
		}
		
		providersByName.put(name, methodRunner);
	}

	

	
	/**
	 * 
	 * @param inflow
	 * @throws IOException
	 */
	public void consume_DECLARE_PROVIDER(ByteInflow inflow) throws IOException {

		/** provider name */
		String providerName = inflow.getStringUTF8();


		NeProvider provider = providersByName.get(providerName);
		if(provider == null) {
			throw new IOException("CANNOT find method for name : "+providerName+" for type: "+prototype.getName());
		}

		
		/** provider code */
		int code = inflow.getUInt8();
		
		provider.code = code;
		int n = providers.length;

		/* extend if necessary */
		if(n <= code) {
			int m = providers.length;
			while(m <= code) { m*=2; }
			NeProvider[] extendedArray = new NeProvider[m]; 
			for(int i=0; i<n; i++) { extendedArray[i] = providers[i]; }
			providers = extendedArray;
		}

		// method runner is now assigned a code
		providers[code] = provider;
	}

}
