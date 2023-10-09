package com.s8.io.csv.type.fields;

import java.lang.reflect.Field;

import com.s8.io.csv.CSV_Enumerable;
import com.s8.io.csv.CSV_Unit;
import com.s8.io.csv.type.Getter;
import com.s8.io.csv.type.Setter;


public abstract class FieldMapping implements Getter, Setter {


	/**
	 * 
	 * @param field
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static FieldMapping build(Field field, CSV_Unit unitConverter) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Class<?> type = field.getType();
		if(type==boolean.class){
			return new BooleanFieldMapping(field);
		}
		else if(type==short.class){
			return new ShortFieldMapping(field);
		}
		else if(type==int.class){
			return new IntegerFieldMapping(field);
		}
		else if(type==long.class){
			return new LongFieldMapping(field);
		}
		else if(type==float.class){
			return new FloatFieldMapping(field, unitConverter);
		}
		else if(type==double.class){
			return new DoubleFieldMapping(field, unitConverter);
		}
		else if(type==String.class){
			return new StringFieldMapping(field);
		}
		else if(type.isEnum()){
			return new EnumFieldMapping(field);
		}
		else if(CSV_Enumerable.class.isAssignableFrom(type)) {
			return new QxEnumerableFieldMapping(field);
		}
		else {
			throw new RuntimeException("Type is not primitive : "+type.getName());
		}
	}


	protected Field field;

	public FieldMapping(Field field) {
		super();
		this.field = field;
	}
	
	
}
