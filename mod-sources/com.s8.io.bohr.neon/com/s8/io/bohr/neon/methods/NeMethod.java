package com.s8.io.bohr.neon.methods;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.objects.web.functions.NeFunction;
import com.s8.io.bohr.neon.core.NeBranch;
import com.s8.io.bohr.neon.core.NeObjectTypeMethods;
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
public abstract class NeMethod {


	public NeObjectTypeMethods prototype;
	/**
	 * 
	 */
	public int code;

	public final int ordinal;


	public final String name;


	/**
	 * 
	 * @param name
	 */
	public NeMethod(NeObjectTypeMethods prototype, String name, int ordinal) {
		super();
		this.prototype = prototype;
		this.name = name;
		this.ordinal = ordinal;
	}


	public abstract long getSignature();


	/**
	 * 
	 * @param branch
	 * @param inflow
	 * @param lambda
	 * @throws IOException
	 */
	public abstract void run(NeBranch branch, S8AsyncFlow flow, ByteInflow inflow, NeFunction function) throws IOException;






	/**
	 * 
	 * @param {ByteInflow} inflow 
	 * @throws IOException 
	 * @returns {NeFieldParser}
	 */
	public static long parseFormat(ByteInflow inflow) throws IOException {

		int code = inflow.getUInt8();

		switch (code) {
		
		/* <specials> */
		case BOHR_Types.VOID: return VoidNeMethod.SIGNATURE;
		/* </specials> */


		/* <structure> */
		case BOHR_Types.ARRAY: return parseArrayFormat(inflow);
		/* </structure> */

		/* <bytes> */
		case BOHR_Types.SERIAL: throw new IOException("Unsupported serial");
		/* </bytes> */


		/* <booleans> */
		case BOHR_Types.BOOL8: return Bool8NeMethod.SIGNATURE;
		/* </booleans> */

		/* <unsigned-integers> */
		case BOHR_Types.UINT8: return UInt8NeMethod.SIGNATURE;
		case BOHR_Types.UINT16: return UInt16NeMethod.SIGNATURE;
		case BOHR_Types.UINT32: return UInt32NeMethod.SIGNATURE;
		case BOHR_Types.UINT64: return UInt64NeMethod.SIGNATURE;
		/* </unsigned-integers> */

		/* <signed-integers> */
		case BOHR_Types.INT8: return Int8NeMethod.SIGNATURE;
		case BOHR_Types.INT16: return Int16NeMethod.SIGNATURE;
		case BOHR_Types.INT32: return Int32NeMethod.SIGNATURE;
		case BOHR_Types.INT64: return Int64NeMethod.SIGNATURE;
		/* </signed-integers> */

		/* <float> */
		case BOHR_Types.FLOAT32: return Float32NeMethod.SIGNATURE;
		case BOHR_Types.FLOAT64: return Float64NeMethod.SIGNATURE;
		/* </float> */

		/* <string> */
		case BOHR_Types.STRING_UTF8: return StringUTF8NeMethod.SIGNATURE;
		/* </string> */

		/* <object> */
		// case BOHR_Types.S8OBJECT: return S8ObjectNeMethodRunner.SIGNATURE;
		/* </object> */

		default: throw new IOException("Unsupported BOHR type code: " + code);
		}
	}


	/**
	 * 
	 * @param inflow
	 * @return
	 * @throws IOException
	 */
	private static long parseArrayFormat(ByteInflow inflow) throws IOException {
		int code = inflow.getUInt8();
		switch (code) {

		case BOHR_Types.BOOL8: return Bool8ArrayNeMethod.SIGNATURE;

		case BOHR_Types.UINT8: return UInt8ArrayNeMethod.SIGNATURE;
		case BOHR_Types.UINT16: return UInt16ArrayNeMethod.SIGNATURE;
		case BOHR_Types.UINT32: return UInt32ArrayNeMethodRunner.SIGNATURE;
		case BOHR_Types.UINT64: return UInt64ArrayNeMethodRunner.SIGNATURE;

		case BOHR_Types.INT8: return Int8ArrayNeMethodRunner.SIGNATURE;
		case BOHR_Types.INT16: return Int16ArrayNeMethodRunner.SIGNATURE;
		case BOHR_Types.INT32: return Int32ArrayNeMethodRunner.SIGNATURE;
		case BOHR_Types.INT64: return Int64ArrayNeMethodRunner.SIGNATURE;

		case BOHR_Types.FLOAT32: return Float32ArrayNeMethod.SIGNATURE;
		case BOHR_Types.FLOAT64: return Float64ArrayNeMethod.SIGNATURE;

		case BOHR_Types.STRING_UTF8: return StringUTF8ArrayNeMethod.SIGNATURE;
		//case BOHR_Types.S8OBJECT: return S8ObjectArrayNeMethodRunner.SIGNATURE;

		default: throw new IOException("Unsupported BOHR ARRAY type code: " + code);
		}
	}

}
