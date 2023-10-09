package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.s8.io.csv.CSV_Enumerable;
import com.s8.io.csv.CSV_Enumerable.Prototype;

public class QxEnumerableFieldMapping extends FieldMapping {
	
	private CSV_Enumerable.Prototype<? extends CSV_Enumerable> proto;
	
	@SuppressWarnings("unchecked")
	public QxEnumerableFieldMapping(Field field) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		super(field);
		Class<?> type = field.getType();
		Field protoStaticField = type.getField("PROTOTYPE");
		proto = (Prototype<? extends CSV_Enumerable>) protoStaticField.get(null);	
	}

	@Override
	public String get(Object object) throws IllegalArgumentException, IllegalAccessException {
		CSV_Enumerable enumerable = (CSV_Enumerable) field.get(object);
		return enumerable.getName();
	}

	@Override
	public void set(String value, Object object)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		CSV_Enumerable enumerable = proto.getByName(value);
		field.set(object, enumerable);
	}

}
