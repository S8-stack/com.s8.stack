package com.s8.io.bohr.neon.fields.primitives;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.io.bohr.neon.core.BuildScope;
import com.s8.io.bohr.neon.core.NeObjectTypeFields;
import com.s8.io.bohr.neon.fields.NeFieldValue;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class Bool8NeFieldHandler extends PrimitiveNeFieldHandler {

	
	public final static long SIGNATURE = BOHR_Types.BOOL8;

	public @Override long getSignature() { return SIGNATURE; }



	/**
	 * 
	 * @param name
	 */
	public Bool8NeFieldHandler(NeObjectTypeFields prototype, String name) {
		super(prototype, name);
	}
	


	@Override
	public void publishEncoding(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(BOHR_Types.BOOL8);
	}
	

	/**
	 * 
	 * @param values
	 * @return
	 */
	public boolean get(NeFieldValue wrapper) {
		return ((Value) wrapper).value;
	}
	
	
	/**
	 * 
	 * @param values
	 * @param value
	 */
	public boolean set(NeFieldValue wrapper, boolean value) {
		return ((Value) wrapper).setValue(value);
	}
	

	/***
	 * 
	 * @param inflow
	 * @return
	 * @throws IOException
	 */
	public static Boolean parse(ByteInflow inflow) throws IOException {
		return inflow.getBool8();
	}
	

	@Override
	public NeFieldValue createValue() {
		return new Value();
	}


	/**
	 * 
	 * @author pierreconvert
	 *
	 */
	public static class Value extends PrimitiveNeFieldHandler.Value {

		private boolean value;

		public Value() {
			super();
		}
		
		public boolean setValue(boolean value) {
			if(this.value != value) {
				this.value = value;
				this.hasDelta = true;	
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public void compose(ByteOutflow outflow) throws IOException {
			outflow.putBool8(value);
		}
		
		@Override
		public void parse(ByteInflow inflow, BuildScope scope) throws IOException {
			value = inflow.getBool8();
		}
	}


}
