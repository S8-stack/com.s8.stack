package com.s8.io.xml.handler.type.elements.setters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.s8.io.xml.parser.PrimitiveParsedScope;
import com.s8.io.xml.parser.PrimitiveParsedScope.Callback;
import com.s8.io.xml.parser.XML_ParsingException;
import com.s8.io.xml.parser.XML_StreamReader;



/**
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class DoubleElementSetter extends PrimitiveElementSetter {


	public final static Prototype PROTOTYPE = new Prototype() {
		
		@Override
		public boolean matches(Class<?> fieldType) {
			return fieldType==double.class;
		}
		
		@Override
		public ElementSetter.Builder create(Method method) {
			return new DoubleElementSetter.Builder(method);
		}
	};
	
	
	public static class Builder extends PrimitiveElementSetter.Builder {

		public Builder(Method method) {
			super(method);
		}

		@Override
		public ElementSetter createSetter() {
			return new DoubleElementSetter(method);
		}
	}
	
	public DoubleElementSetter(Method method) {
		super(method);
	}

	@Override
	protected Callback getCallback(Object object, XML_StreamReader.Point point) {

		return new PrimitiveParsedScope.Callback() {

			@Override
			public void set(String value) throws XML_ParsingException {
				double var = Double.valueOf(value);
				try {
					method.invoke(object, var);
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new XML_ParsingException(point, e.getMessage());
				}
			}
		};
	}
}