package com.s8.api.objects.web.functions.objects;

import java.util.List;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.functions.NeFunction;


@FunctionalInterface
public interface ObjectsListNeFunction<T extends WebS8Object> extends NeFunction {
	
	
	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.S8OBJECT;

	public @Override default long getSignature() { return SIGNATURE; }
	
	
	public abstract void run(S8AsyncFlow flow, List<T> arg);
	
}
