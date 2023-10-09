package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

public class LongFieldMapping extends FieldMapping {

	public LongFieldMapping(Field field) {
		super(field);
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		field.setLong(object, Long.valueOf(value));
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		return Long.toString(field.getLong(object));
	}
}
