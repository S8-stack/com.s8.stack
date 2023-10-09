package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class EnumFieldMapping extends FieldMapping {

	private Class<?> enumType;

	private Map<String, Object> enumConstants;

	public EnumFieldMapping(Field field) {
		super(field);
		this.enumType = field.getType();
		Object[] constantsArray = enumType.getEnumConstants();
		enumConstants = new HashMap<String, Object>(constantsArray.length);
		for(Object value : constantsArray) {
			enumConstants.put(((Enum<?>) value).name(), value);
		}
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		Object enumConstant = enumConstants.get(value);
		if(enumConstant==null) {
			throw new IllegalArgumentException(value+" is not a valid enum value for: "
					+enumType.getName());
		}
		field.set(enumConstant, value);
	}

	@Override
	public String get(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		Object enumConstant = field.get(object);
		return ((Enum<?>) enumConstant).name();
	}
}
