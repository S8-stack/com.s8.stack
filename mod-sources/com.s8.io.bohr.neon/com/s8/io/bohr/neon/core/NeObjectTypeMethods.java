package com.s8.io.bohr.neon.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.io.bohr.neon.methods.NeMethod;
import com.s8.io.bohr.neon.methods.arrays.Bool8ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.Float32ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.Float64ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.Int16ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.Int32ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.Int64ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.Int8ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.StringUTF8ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.UInt16ArrayNeMethod;
import com.s8.io.bohr.neon.methods.arrays.UInt32ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.UInt64ArrayNeMethodRunner;
import com.s8.io.bohr.neon.methods.arrays.UInt8ArrayNeMethod;
import com.s8.io.bohr.neon.methods.objects.ListNeMethod;
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
public class NeObjectTypeMethods {


	/**
	 * The name associated to this object type
	 */
	public final NeObjectTypeHandler prototype;

	/**
	 * Code-based. Code is defined by inbound
	 */
	private NeMethod[] methods;

	private Map<String, NeMethod> methodByName;

	private int nextMethodOrdinal = 0;

	
	
	public NeObjectTypeMethods(NeObjectTypeHandler prototype) {
		super();
		
		this.prototype = prototype;


		this.methods = new NeMethod[2];
		this.methodByName = new HashMap<>();
	}




	public final static String RUNTIME_MODFICATION_ERROR_MESSAGE = "Prototype can only be edited at compile time";


	
	public NeMethod getMethod(int ordinal) {
		return methods[ordinal];
	}
	
	
	
	public VoidNeMethod getVoidMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != VoidNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (VoidNeMethod) method;
		}
		else {
			VoidNeMethod newMethod = new VoidNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}
	



	public Bool8NeMethod getBool8Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Bool8NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Bool8NeMethod) method;
		}
		else {
			Bool8NeMethod newMethod = new Bool8NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	public Bool8ArrayNeMethod getBool8ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Bool8ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Bool8ArrayNeMethod) method;
		}
		else {
			Bool8ArrayNeMethod newMethod = new Bool8ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}
	


	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt8NeMethod getUInt8Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt8NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt8NeMethod) method;
		}
		else {
			UInt8NeMethod newMethod = new UInt8NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt8ArrayNeMethod getUInt8ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt16ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt8ArrayNeMethod) method;
		}
		else {
			UInt8ArrayNeMethod newMethod = new UInt8ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}

	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt16NeMethod getUInt16Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt8NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt16NeMethod) method;
		}
		else {
			UInt16NeMethod newMethod = new UInt16NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}

	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt16ArrayNeMethod getUInt16ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt16ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt16ArrayNeMethod) method;
		}
		else {
			UInt16ArrayNeMethod newMethod = new UInt16ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt32NeMethod getUInt32Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt32NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt32NeMethod) method;
		}
		else {
			UInt32NeMethod newMethod = new UInt32NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt32ArrayNeMethodRunner getUInt32ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt32ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt32ArrayNeMethodRunner) method;
		}
		else {
			UInt32ArrayNeMethodRunner newMethod = new UInt32ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt64NeMethod getUInt64Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt64NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt64NeMethod) method;
		}
		else {
			UInt64NeMethod newMethod = new UInt64NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UInt64ArrayNeMethodRunner getUInt64ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != UInt64ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (UInt64ArrayNeMethodRunner) method;
		}
		else {
			UInt64ArrayNeMethodRunner newMethod = new UInt64ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int8NeMethod getInt8Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int8NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int8NeMethod) method;
		}
		else {
			Int8NeMethod newMethod = new Int8NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int8ArrayNeMethodRunner getInt8ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int8ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int8ArrayNeMethodRunner) method;
		}
		else {
			Int8ArrayNeMethodRunner newMethod = new Int8ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int16NeMethod getInt16Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int16NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int16NeMethod) method;
		}
		else {
			Int16NeMethod newMethod = new Int16NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int16ArrayNeMethodRunner getInt16ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int16ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int16ArrayNeMethodRunner) method;
		}
		else {
			Int16ArrayNeMethodRunner newMethod = new Int16ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int32NeMethod getInt32Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int32NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int32NeMethod) method;
		}
		else {
			Int32NeMethod newMethod = new Int32NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int32ArrayNeMethodRunner getInt32ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int32ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int32ArrayNeMethodRunner) method;
		}
		else {
			Int32ArrayNeMethodRunner newMethod = new Int32ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	


	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int64NeMethod getInt64Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int64NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int64NeMethod) method;
		}
		else {
			Int64NeMethod newMethod = new Int64NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	/**
	 * 
	 * @param name
	 * @return
	 */
	public Int64ArrayNeMethodRunner getInt64ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Int64ArrayNeMethodRunner.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); }
			return (Int64ArrayNeMethodRunner) method;
		}
		else {
			Int64ArrayNeMethodRunner newMethod = new Int64ArrayNeMethodRunner(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	public Float32NeMethod getFloat32Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Float32NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Float32NeMethod) method;
		}
		else {
			Float32NeMethod newMethod = new Float32NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	public Float32ArrayNeMethod getFloat32ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Float32ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Float32ArrayNeMethod) method;
		}
		else {
			Float32ArrayNeMethod newMethod = new Float32ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	public Float64NeMethod getFloat64Method(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Float64NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Float64NeMethod) method;
		}
		else {
			Float64NeMethod newMethod = new Float64NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	public Float64ArrayNeMethod getFloat64ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != Float64ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (Float64ArrayNeMethod) method;
		}
		else {
			Float64ArrayNeMethod newMethod = new Float64ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	public StringUTF8NeMethod getStringUTF8NeMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != StringUTF8NeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (StringUTF8NeMethod) method;
		}
		else {
			StringUTF8NeMethod newMethod = new StringUTF8NeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}




	public StringUTF8ArrayNeMethod getStringUTF8ArrayMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != StringUTF8ArrayNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (StringUTF8ArrayNeMethod) method;
		}
		else {
			StringUTF8ArrayNeMethod newMethod = new StringUTF8ArrayNeMethod(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}



	@SuppressWarnings("unchecked")
	public <T extends WebS8Object>  ObjectNeMethod<T> getObjectMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != ObjectNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (ObjectNeMethod<T>) method;
		}
		else {
			ObjectNeMethod<T> newMethod = new ObjectNeMethod<>(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}


	@SuppressWarnings("unchecked")
	public <T extends WebS8Object> ListNeMethod<T> getObjListMethod(String name) {
		NeMethod method = methodByName.get(name);
		if(method != null) {
			if(method.getSignature() != ListNeMethod.SIGNATURE) { 
				throw new RuntimeException("Cannot change field signature"); 
			}
			return (ListNeMethod<T>) method;
		}
		else {
			ListNeMethod<T> newMethod = new ListNeMethod<T>(this, name, nextMethodOrdinal++);
			appendMethod(newMethod);
			return newMethod;
		}
	}





	/**
	 * Assign ordinal
	 * @param field
	 */
	private void appendMethod(NeMethod methodRunner) {
		
		String name = methodRunner.name;
		
		if(methodByName.containsKey(name)) {
			System.err.println("NE_COMPILE_ERROR: METHOD name conflict: "+name);
		}
		
		methodByName.put(name, methodRunner);
	}

	

	public void consume_DECLARE_METHOD(ByteInflow inflow) throws IOException {

		/* method name */
		String methodName = inflow.getStringUTF8();


		NeMethod methodRunner = methodByName.get(methodName);
		if(methodRunner == null) {
			throw new IOException("CANNOT find method for name : "+methodName+" for type: "+prototype.getName());
		}

		
		long format = NeMethod.parseFormat(inflow);
		if(format != methodRunner.getSignature()) {
			System.err.println("Mismatch in signature for method: "+methodName);
			System.err.println("\tDeclared by front: "+format);
			System.err.println("\tDeclared by back: "+methodRunner.getSignature());
		}
		

		/** method code */
		int code = inflow.getUInt8();
		
		methodRunner.code = code;
		int n = methods.length;

		/* extend if necessary */
		if(n <= code) {
			int m = methods.length;
			while(m <= code) { m*=2; }
			NeMethod[] extended = new NeMethod[m]; 
			for(int i=0; i<n; i++) { extended[i] = methods[i]; }
			methods = extended;
		}

		// method runner is now assined a code
		methods[code] = methodRunner;
	}

}
