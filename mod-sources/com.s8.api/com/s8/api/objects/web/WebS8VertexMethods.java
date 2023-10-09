package com.s8.api.objects.web;

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
import com.s8.api.objects.web.lambdas.arrays.Bool8ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.Float32ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.Float64ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.StringUTF8ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.UInt16ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.UInt32ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.UInt64ArrayLambda;
import com.s8.api.objects.web.lambdas.arrays.UInt8ArrayLambda;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.api.objects.web.lambdas.primitives.Bool8Lambda;
import com.s8.api.objects.web.lambdas.primitives.Float32Lambda;
import com.s8.api.objects.web.lambdas.primitives.Float64Lambda;
import com.s8.api.objects.web.lambdas.primitives.Int16Lambda;
import com.s8.api.objects.web.lambdas.primitives.Int32Lambda;
import com.s8.api.objects.web.lambdas.primitives.Int64Lambda;
import com.s8.api.objects.web.lambdas.primitives.Int8Lambda;
import com.s8.api.objects.web.lambdas.primitives.StringUTF8Lambda;
import com.s8.api.objects.web.lambdas.primitives.UInt16Lambda;
import com.s8.api.objects.web.lambdas.primitives.UInt32Lambda;
import com.s8.api.objects.web.lambdas.primitives.UInt64Lambda;
import com.s8.api.objects.web.lambdas.primitives.UInt8Lambda;


/**
 * 
 * @author pierreconvert
 *
 */
public interface WebS8VertexMethods {


	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setVoidMethod(String name, VoidNeFunction function);

	
	public default void setVoidMethodLambda(String name, VoidLambda lambda) {
		setVoidMethod(name, (flow) -> flow.runBlock(0, () -> lambda.operate()).send());
	}
	
	


	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setBool8Method(String name, Bool8NeFunction function);

	public default void setBool8MethodLambda(String name, Bool8Lambda lambda) {
		setBool8Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	


	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setBool8ArrayMethod(String name, Bool8ArrayNeFunction function);

	
	public default void setBool8ArrayMethodLambda(String name, Bool8ArrayLambda lambda) {
		setBool8ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}

	


	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setUInt8Method(String name, UInt8NeFunction function);

	
	/**
	 * 
	 * @param name
	 * @param lambda
	 */
	public default void setUInt8MethodLambda(String name, UInt8Lambda lambda) {
		setUInt8Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	

	
	public void setUInt8ArrayMethod(String name, UInt8ArrayNeFunction function);

	public default void setUInt8ArrayMethodLambda(String name, UInt8ArrayLambda lambda) {
		setUInt8ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}

	

	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setUInt16Method(String name, UInt16NeFunction function);
	
	
	public default void setUInt16MethodLambda(String name, UInt16Lambda lambda) {
		setUInt16Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	


	
	public void setUInt16ArrayMethod(String name, UInt16ArrayNeFunction function);

	public default void setUInt16ArrayMethodLambda(String name, UInt16ArrayLambda lambda) {
		setUInt16ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}

	

	
	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setUInt32Method(String name, UInt32NeFunction function);

	public default void setUInt32MethodLambda(String name, UInt32Lambda lambda) {
		setUInt32Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	

	
	
	public void setUInt32ArrayMethod(String name, UInt32ArrayNeFunction function);

	
	public default void setUInt32ArrayMethodLambda(String name, UInt32ArrayLambda lambda) {
		setUInt32ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	


	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setUInt64Method(String name, UInt64NeFunction function);
	
	
	public default void setUInt64MethodLambda(String name, UInt64Lambda lambda) {
		setUInt64Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	


	

	public void setUInt64ArrayMethod(String name, UInt64ArrayNeFunction function);

	public default void setUInt64ArrayMethodLambda(String name, UInt64ArrayLambda lambda) {
		setUInt64ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	

	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setInt8Method(String name, Int8NeFunction function);
	
	public default void setInt8MethodLambda(String name, Int8Lambda lambda) {
		setInt8Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	

	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setInt16Method(String name, Int16NeFunction function);
	
	/**
	 * 
	 * @param name
	 * @param lambda
	 */
	public default void setInt16MethodLambda(String name, Int16Lambda lambda) {
		setInt16Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	
	

	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setInt32Method(String name, Int32NeFunction function);
	
	public default void setInt32MethodLambda(String name, Int32Lambda lambda) {
		setInt32Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	

	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setInt64Method(String name, Int64NeFunction function);
	
	
	public default void setInt64MethodLambda(String name, Int64Lambda lambda) {
		setInt64Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	

	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setFloat32Method(String name, Float32NeFunction function);

	public default void setFloat32MethodLambda(String name, Float32Lambda lambda) {
		setFloat32Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	

	
	
	public void setFloat32ArrayMethod(String name, Float32ArrayNeFunction function);

	public default void setFloat32ArrayMethodLambda(String name, Float32ArrayLambda lambda) {
		setFloat32ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}



	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setFloat64Method(String name, Float64NeFunction function);

	public default void setFloat64MethodLambda(String name, Float64Lambda lambda) {
		setFloat64Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	


	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setFloat64ArrayMethod(String name, Float64ArrayNeFunction function);

	public default void setFloat64ArrayMethodLambda(String name, Float64ArrayLambda lambda) {
		setFloat64ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	


	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setStringUTF8Method(String name, StringUTF8NeFunction function);
	

	public default void setStringUTF8MethodLambda(String name, StringUTF8Lambda lambda) {
		setStringUTF8Method(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}
	

	
	
	
	
	/**
	 * 
	 * @param name
	 * @param function
	 */
	public void setStringUTF8ArrayMethod(String name, StringUTF8ArrayNeFunction function);

	public default void setStringUTF8ArrayMethodLambda(String name, StringUTF8ArrayLambda lambda) {
		setStringUTF8ArrayMethod(name, (flow, value) -> flow.runBlock(0, () -> lambda.operate(value)).send());
	}


	
	/**
	 * 
	 * @param <T>
	 * @param name
	 * @return
	 */
	public <T extends WebS8Object> void setObjectMethod(String name, ObjectNeFunction<T> function);


	
}
