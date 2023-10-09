package com.s8.io.bohr.neon.methods.primitives;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.primitives.StringUTF8NeFunction;
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
public class StringUTF8NeMethod extends NeMethod {


	public interface Lambda {

		public void operate(String arg);

	}


	public final static long SIGNATURE = BOHR_Types.STRING_UTF8;

	public @Override long getSignature() { return SIGNATURE; }


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public StringUTF8NeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException {
		String arg = inflow.getStringUTF8();
		((StringUTF8NeFunction) function).run(flow, arg);
	}

}
