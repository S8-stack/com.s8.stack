package com.s8.io.csv.type.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LongMethodSetter extends MethodSetter {

	protected LongMethodSetter(Method method) {
		super(method);
	}

	@Override
	public void set(String value, Object object)
			throws
			NumberFormatException,
			IllegalArgumentException, 
			IllegalAccessException,
			InvocationTargetException {
		method.invoke(object, Long.valueOf(value));
	}

}
