package com.s8.io.csv.type.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShortMethodSetter extends MethodSetter {

	public ShortMethodSetter(Method method) {
		super(method);
	}

	@Override
	public void set(String value, Object object)
			throws
			NumberFormatException,
			IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException {
		method.invoke(object, Short.valueOf(value));
	}

}
