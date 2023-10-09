package com.s8.api.objects.web.functions.arrays;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;

/**
 * 
 * @author pierreconvert
 *
 */

@FunctionalInterface
public interface Int8ArrayNeFunction extends NeFunction {

	
	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.INT8;

	public @Override default long getSignature() { return SIGNATURE; }
	
	public abstract void run(S8AsyncFlow flow, int[] arg);
}
