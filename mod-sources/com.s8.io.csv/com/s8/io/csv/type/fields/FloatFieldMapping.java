package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

import com.s8.io.csv.CSV_Unit;

public class FloatFieldMapping extends FieldMapping {
	
	private final CSV_Unit unitConverter;

	public FloatFieldMapping(Field field, CSV_Unit unitConverter) {
		super(field);
		this.unitConverter = unitConverter;
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		float secondaryValue = Float.valueOf(value);
		double primaryValue = unitConverter!=null ? unitConverter.revert(secondaryValue) : secondaryValue; 
		field.setFloat(object, (float) primaryValue);
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		double primaryValue = field.getFloat(object);
		double secondaryValue = unitConverter!=null ? unitConverter.convert(primaryValue) : primaryValue;
		return Float.toString((float) secondaryValue);
	}
}
