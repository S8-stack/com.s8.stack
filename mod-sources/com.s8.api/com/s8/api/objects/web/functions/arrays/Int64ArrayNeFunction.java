package com.s8.api.objects.web.functions.arrays;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;

@FunctionalInterface
public interface Int64ArrayNeFunction extends NeFunction {
	
	
	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.INT64;

	public @Override default long getSignature() { return SIGNATURE; }

	public abstract void operate(S8AsyncFlow flow, long[] arg);
}
