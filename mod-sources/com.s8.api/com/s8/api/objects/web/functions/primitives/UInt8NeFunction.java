package com.s8.api.objects.web.functions.primitives;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;


@FunctionalInterface
public interface UInt8NeFunction extends NeFunction {
	
	
	
	public final static long SIGNATURE = BOHR_Types.BOOL8;

	
	@Override
	public default long getSignature() { 
		return SIGNATURE; 
	}
	

	
	public abstract void run(S8AsyncFlow flow, int arg);
}
