package com.s8.api.objects.web.functions.primitives;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;


@FunctionalInterface
public interface Bool8NeFunction extends NeFunction {
	
	
	public final static long SIGNATURE = BOHR_Types.BOOL8;

	
	
	public @Override default long getSignature() { return SIGNATURE; }
	
	
	public abstract void run(S8AsyncFlow flow, boolean arg);

}
