package com.s8.io.bohr.neon.fields.primitives;

import com.s8.io.bohr.neon.core.NeObjectTypeFields;
import com.s8.io.bohr.neon.fields.NeFieldHandler;
import com.s8.io.bohr.neon.fields.NeFieldValue;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public abstract class PrimitiveNeFieldHandler extends NeFieldHandler {

	
	/**
	 * 
	 * @param prototype
	 * @param name
	 */
	public PrimitiveNeFieldHandler(NeObjectTypeFields prototype, String name) {
		super(prototype, name);
	}
	
	
	
	public static abstract class Value extends NeFieldValue {
		public Value() {
			super();
		}
	}
	
	
}
