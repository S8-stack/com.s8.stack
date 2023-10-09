package com.s8.io.bohr.neon.fields.arrays;

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
public class UInt64ArrayNeFieldHandler extends PrimitiveNeFieldHandler {

	public final static long SIGNATURE =  BOHR_Types.ARRAY << 8 & BOHR_Types.UINT64;

	public @Override long getSignature() { return SIGNATURE; }


	public UInt64ArrayNeFieldHandler(NeObjectTypeFields prototype, String name) {
		super(prototype, name);
	}


	@Override
	public void publishEncoding(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(BOHR_Types.ARRAY);
		outflow.putUInt8(BOHR_Types.UINT64);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public long[] get(NeFieldValue wrapper) {
		return ((Value) wrapper).value;
	}


	/**
	 * 
	 * @param values
	 * @param value
	 */
	public void set(NeFieldValue wrapper, long[] value) {
		((Value) wrapper).setValue(value);
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

		private long[] value;

		public Value() {
			super();
		}


		private boolean checkIfHasDelta(long[] value) {
			if(this.value == null && value == null) {
				return false;
			}
			else if((this.value != null && value == null) || (this.value == null && value != null)) {
				return true;
			}
			else { /* this.value != null && value != null */
				int nLeft = this.value.length, nRight = value.length;
				if(nLeft != nRight) {
					return true;
				}
				else {
					for(int i= 0; i<nLeft; i++) {
						if(this.value[i] != value[i]) { return true; }
					}
					return false;
				}
			}
		}

		public void setValue(long[] value) {
			if(checkIfHasDelta(value)) {
				this.value = value;
				this.hasDelta = true;
			}
		}

		@Override
		public void compose(ByteOutflow outflow) throws IOException {
			if(value != null) {
				int length = value.length;
				outflow.putUInt7x(length);
				for(int i=0; i<length; i++) {
					outflow.putUInt64(value[i]);		
				}
			}
			else {
				outflow.putUInt7x(-1);
			}
		}

		@Override
		public void parse(ByteInflow inflow, BuildScope scope) throws IOException {
			int length = (int) inflow.getUInt7x();
			if(length >=0 ) {
				value = new long[length];
				for(int i=0; i<length; i++) {
					value[i] = inflow.getUInt64();
				}
			}
			else {
				value = null;
			}
		}
	}
}
