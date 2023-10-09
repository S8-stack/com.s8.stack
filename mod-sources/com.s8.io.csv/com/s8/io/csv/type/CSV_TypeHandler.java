package com.s8.io.csv.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.s8.io.csv.CSV_Mapping;
import com.s8.io.csv.CSV_Unit;
import com.s8.io.csv.type.fields.FieldMapping;
import com.s8.io.csv.type.methods.MethodSetter;

/**
 * 
 * @author pc
 *
 * @param <T>
 */
public class CSV_TypeHandler {


	private Class<?> type;

	private static class Entry {

		public Field field;

		public Method setMethod;

		
		public Getter getter;
		
		public Setter setter;
		
		public Setter getSetter(CSV_Unit unitConverter) 
				throws 
				NoSuchFieldException, 
				SecurityException, 
				IllegalArgumentException, 
				IllegalAccessException {
			if(setter == null) {
				if(field != null) {
					FieldMapping mapping = FieldMapping.build(field, unitConverter); 
					getter = mapping;
					setter = mapping;
				}
				else if(setMethod != null){
					setter = MethodSetter.build(setMethod, unitConverter);
				}
			}
			return setter;
		}
		
		public Getter getGetter(CSV_Unit unitConverter) {
			return getter;
		}
	}

	private Map<String, Entry> entries = new HashMap<>();




	/**
	 * 
	 * @param type
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public CSV_TypeHandler(Class<?> type) 
			throws 
			NoSuchMethodException, 
			SecurityException, 
			NoSuchFieldException, 
			IllegalArgumentException, 
			IllegalAccessException 
	{
		super();

		this.type = type;
		explore();
	}


	private Entry getEntry(String tag) {
		return entries.computeIfAbsent(tag, k -> new Entry());
	}


	private void explore() {
		CSV_Mapping annotation;

		// screen fields
		for(Field field : type.getFields()){
			annotation = field.getAnnotation(CSV_Mapping.class);
			if(annotation!=null){
				Entry entry = getEntry(annotation.tag());
				entry.field = field;
			}
		}

		// screen setters
		for(Method method : type.getMethods()){
			annotation = method.getAnnotation(CSV_Mapping.class);
			if(annotation!=null){
				Entry entry = getEntry(annotation.tag());
				entry.setMethod = method;
				// TODO same things for getters
			}
		}
	}
	

	public Class<?> getType(){
		return type;
	}

	public Getter getGetter(String tag, CSV_Unit unitConverter) {
		return getEntry(tag).getGetter(unitConverter);
	}

	public Setter getSetter(String tag, CSV_Unit unitConverter) throws 
		NoSuchFieldException, 
		SecurityException, 
		IllegalArgumentException, 
		IllegalAccessException {
		return getEntry(tag).getSetter(unitConverter);
	}

}

