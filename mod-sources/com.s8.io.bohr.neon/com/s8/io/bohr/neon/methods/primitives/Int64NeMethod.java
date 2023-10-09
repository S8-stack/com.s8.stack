package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.primitives.Int64NeFunction;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectTypeMethods;
import com.s8.io.bohr.neon.methods.NeMethod;


/**
 * 
 * @author pierreconvert
 *
 */
public class Int64NeMethod extends NeMethod {

	public final static long SIGNATURE = BOHR_Types.INT64;
	
	public @Override long getSignature() { return SIGNATURE; }

	
	public Int64NeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}

	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		long arg =  inflow.getInt64();
		((Int64NeFunction) function).run(flow, arg);
	}
}
