package com.s8.io.csv.type.methods;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.s8.io.csv.CSV_Unit;
import com.s8.io.csv.type.Setter;


public abstract class MethodSetter implements Setter {


	/**
	 * 
	 * @param field
	 * @return
	 */
	public static MethodSetter build(Method method, CSV_Unit converter){
		Parameter[] parameters = method.getParameters();
		if(parameters.length!=1){
			return null;
			//throw new RuntimeException("Illegal number of paramters for method:"+method.getName());
		}

		Class<?> type = parameters[0].getType();
		if(type==boolean.class){
			return new BooleanMethodSetter(method);
		}
		else if(type==short.class){
			return new ShortMethodSetter(method);
		}
		else if(type==int.class){
			return new IntegerMethodSetter(method);
		}
		else if(type==long.class){
			return new LongMethodSetter(method);
		}
		else if(type==float.class){
			return new FloatMethodSetter(method, converter);
		}
		else if(type==double.class){
			return new DoubleMethodSetter(method, converter);
		}
		else if(type==String.class){
			return new StringMethodSetter(method);
		}
		else {
			//throw new RuntimeException("Type is not primitive : "+type.getName());
			return null;
		}
	}


	protected Method method;

	protected MethodSetter(Method method) {
		super();
		this.method = method;
	}

	

}
