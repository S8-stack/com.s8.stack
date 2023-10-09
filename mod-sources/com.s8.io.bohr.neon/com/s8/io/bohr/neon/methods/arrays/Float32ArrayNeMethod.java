package com.s8.io.bohr.neon.methods.arrays;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.arrays.Float32ArrayNeFunction;
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
public class Float32ArrayNeMethod extends NeMethod {


	public interface Lambda {

		public void operate(float[] arg);

	}


	public final static long SIGNATURE = (BOHR_Types.ARRAY << 8) & BOHR_Types.FLOAT32;


	public @Override long getSignature() { return SIGNATURE; }


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public Float32ArrayNeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		float[] arg = null;
		int length = (int) inflow.getUInt7x();
		if(length >= 0) {
			arg = new float[length];
			for(int i=0; i<length; i++) { arg[i] = inflow.getFloat32(); }
		
		}
		((Float32ArrayNeFunction) function).run(flow, arg);
	}




}
