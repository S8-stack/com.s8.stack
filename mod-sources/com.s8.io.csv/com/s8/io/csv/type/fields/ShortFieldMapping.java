package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;


/**
 * 
 * @author pierreconvert
 *
 */
public class ShortFieldMapping extends FieldMapping {

	public ShortFieldMapping(Field field) {
		super(field);
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		field.setShort(object, Short.valueOf(value));
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		return Short.toString(field.getShort(object));
	}
}