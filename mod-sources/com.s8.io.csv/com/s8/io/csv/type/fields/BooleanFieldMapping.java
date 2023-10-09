package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

public class BooleanFieldMapping extends FieldMapping {

	public BooleanFieldMapping(Field field) {
		super(field);
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		field.setBoolean(object, Boolean.valueOf(value));
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		return Boolean.toString(field.getBoolean(object));
	}
}