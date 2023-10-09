package com.s8.io.csv.type.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.s8.io.csv.CSV_Unit;

public class DoubleMethodSetter extends MethodSetter {

	
	private CSV_Unit converter;
	

	public DoubleMethodSetter(Method method, CSV_Unit converter) {
		super(method);
		this.converter = converter;
	}

	@Override
	public void set(String value, Object object)
			throws
			NumberFormatException,
			IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException {
		double secondaryValue = Double.valueOf(value);
		method.invoke(object, converter != null ? converter.revert(secondaryValue) : secondaryValue);
	}

}
