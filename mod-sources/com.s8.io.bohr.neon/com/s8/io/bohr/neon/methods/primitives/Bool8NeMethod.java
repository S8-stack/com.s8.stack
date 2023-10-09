package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.primitives.Bool8NeFunction;
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
public class Bool8NeMethod extends NeMethod {


	public final static long SIGNATURE = BOHR_Types.BOOL8;

	public @Override long getSignature() { return SIGNATURE; }


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public Bool8NeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		boolean arg = inflow.getBool8();
		//if(function.getSignature() != getSignature()) { throw new IOException("Wrong signature"); }
		((Bool8NeFunction) function).run(flow, arg);
	}
}
