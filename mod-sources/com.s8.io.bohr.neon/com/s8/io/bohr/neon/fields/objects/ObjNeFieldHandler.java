package com.s8.io.bohr.neon.fields.objects;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.io.bohr.neon.core.BuildScope;
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
public class ObjNeFieldHandler<T extends WebS8Object> extends NeFieldHandler {

	public final static long SIGNATURE =  BOHR_Types.S8OBJECT;

	public @Override long getSignature() { return SIGNATURE; }

	/**
	 * 
	 * @param view
	 */
	public ObjNeFieldHandler(NeObjectTypeFields prototype, String name) {
		super(prototype, name);
	}


	@Override
	public void publishEncoding(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(BOHR_Types.S8OBJECT);
	}




	/**
	 * 
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(NeFieldValue wrapper) {
		return ((Value<T>) wrapper).value;
	}


	/**
	 * 
	 * @param values
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public boolean set(NeFieldValue wrapper, T value) {
		return ((Value<T>) wrapper).setValue(value);
	}


	@Override
	public NeFieldValue createValue() {
		return new Value<>();
	}



	/**
	 * 
	 * @author pierreconvert
	 *
	 */
	public static class Value<T extends WebS8Object> extends NeFieldValue {

		private T value;

		public Value() {
			super();
		}


		private boolean checkIfHasDelta(T value) {
			return (this.value == null && value != null) || 
					(this.value != null && value == null) ||
					(this.value != null && value != null && !this.value.vertex.getId().equals(value.vertex.getId()));

		}


		/**
		 * 
		 * @param value
		 */
		public boolean setValue(T value) {
			if(checkIfHasDelta(value)) {
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
			outflow.putStringUTF8(value != null ? value.vertex.getId() : null);
		}


		@Override
		public void parse(ByteInflow inflow, BuildScope scope) throws IOException {

			// get index
			String index = inflow.getStringUTF8();

			if(index != null) {
				scope.appendBinding(new BuildScope.Binding() {

					@SuppressWarnings("unchecked")
					@Override
					public void resolve(BuildScope scope) throws IOException {
						Value.this.value = (T) scope.retrieveObject(index);
					}
				});
			}
			else {
				value = null;
			}
		}
	}	
}
