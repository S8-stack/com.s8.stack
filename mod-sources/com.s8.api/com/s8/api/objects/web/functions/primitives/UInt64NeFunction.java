package com.s8.api.objects.web.functions.primitives;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;

/**
 * 
 * @author pierreconvert
 *
 */

@FunctionalInterface
public interface UInt64NeFunction extends NeFunction {

	
	
	public final static long SIGNATURE = BOHR_Types.UINT64;

	
	@Override
	public default long getSignature() { 
		return SIGNATURE; 
	}
	
	

	/**
	 * 
	 * @param arg
	 */
	public abstract void run(S8AsyncFlow flow, long arg);
	
}

