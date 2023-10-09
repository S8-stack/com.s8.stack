package com.s8.io.bohr.neon.methods.arrays;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.arrays.UInt32ArrayNeFunction;
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
public class Int32ArrayNeMethodRunner extends NeMethod {


	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.INT32;
	public @Override long getSignature() { return SIGNATURE; }


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public Int32ArrayNeMethodRunner(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		int[] arg = null;
		int length = (int) inflow.getUInt7x();
		if(length >= 0) {
			arg = new int[length];
			for(int i=0; i<length; i++) { arg[i] = inflow.getInt32(); }
		}
		((UInt32ArrayNeFunction) function).run(flow, arg);
	}
}
