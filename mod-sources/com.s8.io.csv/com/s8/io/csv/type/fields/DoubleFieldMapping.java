package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

import com.s8.io.csv.CSV_Unit;


public class DoubleFieldMapping extends FieldMapping {

	private CSV_Unit unitConverter;

	public DoubleFieldMapping(Field field, CSV_Unit unitConverter) {
		super(field);
		this.unitConverter = unitConverter;
	}
	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		double secondaryValue = Double.valueOf(value);
		double primaryValue = unitConverter!=null ? unitConverter.revert(secondaryValue) : secondaryValue; 
		field.setDouble(object, primaryValue);
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		double primaryValue = field.getDouble(object);
		double secondaryValue = unitConverter!=null ? unitConverter.convert(primaryValue) : primaryValue;
		return Double.toString(secondaryValue);
	}
}