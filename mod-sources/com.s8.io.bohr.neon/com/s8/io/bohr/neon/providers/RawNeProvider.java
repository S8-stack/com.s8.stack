package com.s8.io.bohr.neon.providers;

import java.io.IOException;

import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.flow.delivery.S8WebResourceGenerator;
import com.s8.io.bohr.neon.core.NeObjectTypeProviders;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class RawNeProvider extends NeProvider {


	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public RawNeProvider(NeObjectTypeProviders prototype, String name, int ordinal) {
		super(prototype, name, ordinal);
	}


	@Override
	public void run(S8AsyncFlow delivery, S8WebResourceGenerator generator) throws IOException {
		delivery.deliver(0, generator);
	}
}
