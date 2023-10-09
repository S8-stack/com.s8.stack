package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;


public class IntegerFieldMapping extends FieldMapping {

	public IntegerFieldMapping(Field field) {
		super(field);
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		field.setInt(object, Integer.valueOf(value));
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		return Integer.toString(field.getInt(object));
	}
}