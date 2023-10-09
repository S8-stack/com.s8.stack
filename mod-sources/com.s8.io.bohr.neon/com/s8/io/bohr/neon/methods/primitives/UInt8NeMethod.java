package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.primitives.UInt8NeFunction;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectTypeMethods;
import com.s8.io.bohr.neon.methods.NeMethod;


/**
 * 
 * @author pierreconvert
 *
 */
public class UInt8NeMethod extends NeMethod {

	public final static long SIGNATURE = BOHR_Types.UINT8;
	
	public @Override long getSignature() { return SIGNATURE; }

	
	public UInt8NeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}

	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		int arg =  inflow.getUInt8();
		((UInt8NeFunction) function).run(flow, arg);
	}
}
