package com.s8.io.bohr.neon.fields;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Keywords;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.io.bohr.neon.core.BuildScope;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public abstract class NeFieldValue {



	
	/**
	 * has delta
	 */
	protected boolean hasDelta;
	
	public NeFieldValue() {
		super();
		hasDelta = true;
	}
	


	public boolean hasPendingDelta() {
		return hasDelta;
	}
	
	

	/**
	 * 
	 * @param inflow
	 * @throws IOException
	 */
	public abstract void parse(ByteInflow inflow, BuildScope scope) throws IOException;
	

	/*
	 * publish the value
	 */
	public abstract void compose(ByteOutflow outflow) throws IOException;
	

	
	
	
	/**
	 * 
	 * @param type
	 * @param code
	 * @param outflow
	 * @throws IOException
	 */
	public void publishEntry(int code, ByteOutflow outflow) throws IOException {
		
		/* has delta */
		if(hasDelta) {
			
			// publish field advertiser
			outflow.putUInt8(BOHR_Keywords.SET_VALUE);

			// publish field code
			outflow.putUInt8(code);
			
			// publish field value
			compose(outflow);
			
			// has delta
			hasDelta = false;
		}
	}
	
}
