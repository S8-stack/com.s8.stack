package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

public class StringFieldMapping extends FieldMapping {

	public StringFieldMapping(Field field) {
		super(field);
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		field.set(object, value);
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		return (String) field.get(object);
	}
}