package com.s8.io.csv.type.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.s8.io.csv.CSV_Unit;

public class FloatMethodSetter extends MethodSetter {


	private CSV_Unit converter;
	

	public FloatMethodSetter(Method method, CSV_Unit converter) {
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
		double secondaryValue = Float.valueOf(value);
		float primaryValue = (float) (converter != null ? converter.revert(secondaryValue) : secondaryValue);
		method.invoke(object, primaryValue);
	}
}
