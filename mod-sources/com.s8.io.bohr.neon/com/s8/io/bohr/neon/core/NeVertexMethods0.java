package com.s8.io.bohr.neon.core;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8VertexMethods;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.api.objects.web.functions.arrays.Bool8ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.Float32ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.Float64ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.StringUTF8ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.UInt16ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.UInt32ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.UInt64ArrayNeFunction;
import com.s8.api.objects.web.functions.arrays.UInt8ArrayNeFunction;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.functions.objects.ObjectNeFunction;
import com.s8.api.objects.web.functions.primitives.Bool8NeFunction;
import com.s8.api.objects.web.functions.primitives.Float32NeFunction;
import com.s8.api.objects.web.functions.primitives.Float64NeFunction;
import com.s8.api.objects.web.functions.primitives.Int16NeFunction;
import com.s8.api.objects.web.functions.primitives.Int32NeFunction;
import com.s8.api.objects.web.functions.primitives.Int64NeFunction;
import com.s8.api.objects.web.functions.primitives.Int8NeFunction;
import com.s8.api.objects.web.functions.primitives.StringUTF8NeFunction;
import com.s8.api.objects.web.functions.primitives.UInt16NeFunction;
import com.s8.api.objects.web.functions.primitives.UInt32NeFunction;
import com.s8.api.objects.web.functions.primitives.UInt64NeFunction;
import com.s8.api.objects.web.functions.primitives.UInt8NeFunction;
import com.s8.io.bohr.neon.methods.NeMethod;
import com.s8.io.bohr.neon.methods.arrays.Bool8ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.Float32ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.Float64ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.StringUTF8ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.UInt16ArrayNeMethod;
import com.s8.io.bohr.neon.methods.objects.ObjectNeMethod;
import com.s8.io.bohr.neon.methods.primitives.Bool8NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Float32NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Float64NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Int16NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Int32NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Int64NeMethod;
import com.s8.io.bohr.neon.methods.primitives.Int8NeMethod;
import com.s8.io.bohr.neon.methods.primitives.StringUTF8NeMethod;
import com.s8.io.bohr.neon.methods.primitives.UInt16NeMethod;
import com.s8.io.bohr.neon.methods.primitives.UInt32NeMethod;
import com.s8.io.bohr.neon.methods.primitives.UInt64NeMethod;
import com.s8.io.bohr.neon.methods.primitives.UInt8NeMethod;
import com.s8.io.bohr.neon.methods.zero.VoidNeMethod;


/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeVertexMethods0 implements WebS8VertexMethods {



	public final NeVertex0 vertex;

	public final NeObjectTypeMethods prototype;
	
	private NeFunction[] functions;


	/**
	 * 
	 * @param branch
	 * @param typeName
	 * @param object
	 */
	public NeVertexMethods0(NeVertex0 vertex, NeObjectTypeMethods prototype) {
		super();
		
		this.vertex = vertex;
		this.prototype = prototype;

		functions = new NeFunction[4];
	}




	/**
	 * 
	 * @param method
	 * @return
	 */
	private int getMethodOrdinal(NeMethod method) {
		int ordinal = method.ordinal;
		while(ordinal >= functions.length) {
			int n = functions.length;
			NeFunction[] extendedFunctions = new NeFunction[2 * n];
			for(int i = 0; i < n; i++) { extendedFunctions[i] = functions[i]; }
			functions = extendedFunctions;
		}
		return ordinal;
	}
	
	
	

	public NeFunction getFunction(int ordinal) {
		return functions[ordinal];
	}
	
	
	
	@Override
	public void setVoidMethod(String name, VoidNeFunction function) {

		/* retrieve (or define if first time) method runner */
		VoidNeMethod method = prototype.getVoidMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}






	@Override
	public void setBool8Method(String name, Bool8NeFunction function) {

		/* retrieve (or define if first time) method runner */
		Bool8NeMethod method = prototype.getBool8Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}


	@Override
	public void setBool8ArrayMethod(String name, Bool8ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Bool8ArrayNeMethod methodRunner = prototype.getBool8ArrayMethod(name);
		int ordinal = getMethodOrdinal(methodRunner);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setUInt8Method(String name, UInt8NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt8NeMethod method = prototype.getUInt8Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setUInt8ArrayMethod(String name, UInt8ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt8NeMethod method = prototype.getUInt8Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}

	
	@Override
	public void setUInt16Method(String name, UInt16NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt16NeMethod method = prototype.getUInt16Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	@Override
	public void setUInt16ArrayMethod(String name, UInt16ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt16ArrayNeMethod methodRunner = prototype.getUInt16ArrayMethod(name);
		int ordinal = getMethodOrdinal(methodRunner);
		functions[ordinal] = function;
	}

	
	@Override
	public void setUInt32Method(String name, UInt32NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt32NeMethod method = prototype.getUInt32Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	@Override
	public void setUInt32ArrayMethod(String name, UInt32ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt16ArrayNeMethod methodRunner = prototype.getUInt16ArrayMethod(name);
		int ordinal = getMethodOrdinal(methodRunner);
		functions[ordinal] = function;
	}

	
	
	
	@Override
	public void setUInt64Method(String name, UInt64NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt64NeMethod method = prototype.getUInt64Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setUInt64ArrayMethod(String name, UInt64ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		UInt16ArrayNeMethod methodRunner = prototype.getUInt16ArrayMethod(name);
		int ordinal = getMethodOrdinal(methodRunner);
		functions[ordinal] = function;
	}

	

	@Override
	public void setInt8Method(String name, Int8NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Int8NeMethod method = prototype.getInt8Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setInt16Method(String name, Int16NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Int16NeMethod method = prototype.getInt16Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}


	@Override
	public void setInt32Method(String name, Int32NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Int32NeMethod method = prototype.getInt32Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setInt64Method(String name, Int64NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Int64NeMethod method = prototype.getInt64Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}



	@Override
	public void setFloat32Method(String name, Float32NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Float32NeMethod method = prototype.getFloat32Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	@Override
	public void setFloat32ArrayMethod(String name, Float32ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Float32ArrayNeMethod method = prototype.getFloat32ArrayMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}

	
	@Override
	public void setFloat64Method(String name, Float64NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Float64NeMethod method = prototype.getFloat64Method(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	@Override
	public void setFloat64ArrayMethod(String name, Float64ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		Float64ArrayNeMethod method = prototype.getFloat64ArrayMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}

	@Override
	public void setStringUTF8Method(String name, StringUTF8NeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		StringUTF8NeMethod method = prototype.getStringUTF8NeMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	
	
	
	@Override
	public void setStringUTF8ArrayMethod(String name, StringUTF8ArrayNeFunction function) {
		
		/* retrieve (or define if first time) method runner */
		StringUTF8ArrayNeMethod method = prototype.getStringUTF8ArrayMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}
	



	@Override
	public <T extends WebS8Object> void setObjectMethod(String name, ObjectNeFunction<T> function) {
		/* retrieve (or define if first time) method runner */
		 @SuppressWarnings("unchecked")
		ObjectNeMethod<T> method = (ObjectNeMethod<T>) prototype.getObjectMethod(name);
		int ordinal = getMethodOrdinal(method);
		functions[ordinal] = function;
	}

	

}
