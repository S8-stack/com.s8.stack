package com.s8.api.objects.web.functions.objects;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.functions.NeFunction;

/**
 * 
 * @author pierreconvert
 *
 */
@FunctionalInterface
public interface ObjectNeFunction<T extends WebS8Object> extends NeFunction {

	
	
	public final static long SIGNATURE = BOHR_Types.S8OBJECT;

	
	@Override
	public default long getSignature() { 
		return SIGNATURE; 
	}
	
	
	/**
	 * 
	 * @param arg
	 */
	public abstract void run(S8AsyncFlow flow, T arg);
	
	
}
