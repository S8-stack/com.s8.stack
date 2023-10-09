package com.s8.io.bohr.neon.providers;

import java.io.IOException;

import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.flow.delivery.S8WebResourceGenerator;
import com.s8.io.bohr.neon.core.NeObjectTypeProviders;


/**
 * 
 * @author pierreconvert
 *
 */
public abstract class NeProvider {


	/**
	 * 
	 */
	public final NeObjectTypeProviders prototype;
	

	public final int ordinal;


	public final String name;


	
	/**
	 * 
	 */
	public int code;
	

	/**
	 * 
	 * @param name
	 */
	public NeProvider(NeObjectTypeProviders prototype, String name, int ordinal) {
		super();
		this.prototype = prototype;
		this.name = name;
		this.ordinal = ordinal;
	}


	/**
	 * 
	 * @param function
	 * @return
	 * @throws IOException
	 */
	public abstract void run(S8AsyncFlow delivery, S8WebResourceGenerator function) throws IOException;
	
}
