package com.s8.io.joos.fields.arrays;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.joos.ParsingException;
import com.s8.io.joos.composing.ComposingScope;
import com.s8.io.joos.fields.PrimitivesArrayFieldHandler;
import com.s8.io.joos.parsing.JOOS_ParsingException;
import com.s8.io.joos.parsing.ArrayScope;
import com.s8.io.joos.parsing.ParsingScope;
import com.s8.io.joos.parsing.AlphaNumericScope;


/**
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class FloatArrayFieldHandler extends PrimitivesArrayFieldHandler {

	public static class Builder extends PrimitivesArrayFieldHandler.Builder {

		public Builder(String name, Field field) {
			super(field);
			handler = new FloatArrayFieldHandler(name, field);
		}
	}
	
	
	public FloatArrayFieldHandler(String name, Field field) {
		super(name, field);
	}

	@Override
	public boolean isItemValid(Object array, int index) {
		return true; // always valid
	}

	@Override
	public void composeItem(Object array, int index, ComposingScope scope) 
			throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
		scope.append(Float.toString(Array.getFloat(array, index)));
	}


	@Override
	public ParsingScope openScope(Object object) {
		
		return new ArrayScope() {
			private List<Float> values = new ArrayList<>();
			
			@Override
			public ParsingScope openItemScope() throws JOOS_ParsingException {
				return new AlphaNumericScope() {
					@Override
					public void setValue(String value) throws JOOS_ParsingException, ParsingException {
						values.add(Float.valueOf(value));
					}
				};
			}

			@Override
			public void close() throws JOOS_ParsingException {
				int length = values.size();
				float[] array = new float[length];
				for(int i=0; i<length; i++) {
					array[i] = values.get(i);
				}
				try {
					FloatArrayFieldHandler.this.set(object, array);
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					throw new JOOS_ParsingException("Failed to set object due to "+e.getMessage());
				}
			}
		};
	}

}
