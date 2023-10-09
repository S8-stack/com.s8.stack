package com.s8.io.bohr.neon.methods.arrays;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.arrays.UInt64ArrayNeFunction;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectTypeMethods;
import com.s8.io.bohr.neon.methods.NeMethod;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class UInt64ArrayNeMethodRunner extends NeMethod {


	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.UINT64;
	public @Override long getSignature() { return SIGNATURE; }


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public UInt64ArrayNeMethodRunner(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		long[] arg = null;
		int length = (int) inflow.getUInt7x();
		if(length >= 0) {
			arg = new long[length];
			for(int i=0; i<length; i++) { arg[i] = inflow.getUInt64(); }
		}
		((UInt64ArrayNeFunction) function).run(flow, arg);
	}
}
