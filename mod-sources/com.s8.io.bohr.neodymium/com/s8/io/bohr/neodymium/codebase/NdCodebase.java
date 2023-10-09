package com.s8.io.bohr.neodymium.codebase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.s8.io.bohr.neodymium.exceptions.NdBuildException;
import com.s8.io.bohr.neodymium.type.NdType;

/**
 * 
 * <h1>Codebase</h1>
 * <h2>S8 Model foundation</h2>
 * <p>
 * The S8 Model is base on a <code>GphCodebase</code> which is meant to be used
 * on massive scale. procedure is as follows
 * </p>
 * <ul>
 * <li>Step<b>#1</b>: Code your .jar with all classes annotated by
 * <code>S8Type</code> and <code>S8Field</code> (and <code>S8Getter</code>/
 * <code>S8Setter</code>). Names used in annotations MUST NOT be changed in the
 * future. Note that is a good idea to a prefixnig (suffixing) hash for
 * successive versions.</li>
 * <li>Step<b>#2</b>: (First-time) Compile your code using
 * <code>GphCodebase.compile()</code>.</li>
 * <li>Step<b>#3</b>: Your <code>GphCodebase</code> is now compiled (which means
 * that all names have been translated into codes with much more efficient
 * memory footprint. You must now save this compilation using <code>GphCodebase.compose()</code>.</li>
 * <li>Step<b>#4</b>: (Subsequent uses) When booting, server must generate <code>GphCodebase</code> using <code>GphCodebaseBuilder</code>, then
 * use the <code>GphCodebase.parse()</code> method to retrieve the proper encoding.</li>
 * </ul>
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NdCodebase {
	
	
	
	
	/**
	 * 
	 * @param classes
	 * @param isVerbose
	 * @return
	 * @throws NdBuildException
	 */
	public static NdCodebase from(Class<?>[] classes, boolean isVerbose) throws NdBuildException {
		NdCodebaseBuilder codebaseBuilder = new NdCodebaseBuilder(isVerbose);
		codebaseBuilder.pushObjectTypes(classes);
		return codebaseBuilder.build();
	}
	
	/**
	 * 
	 * @param classes
	 * @return
	 * @throws NdBuildException
	 */
	public static NdCodebase from(Class<?>... classes) throws NdBuildException {
		return from(classes, false);
	}
	
	public static NdCodebase from_DEBUG(Class<?>... classes) throws NdBuildException {
		return from(classes, true);
	}
	


	
	public final static byte[] OPENING_TAG = "<code:>".getBytes(StandardCharsets.US_ASCII);

	public final static byte[] CLOSING_TAG = "</code>".getBytes(StandardCharsets.US_ASCII);

	
	public long revision;
	
	private boolean isVerbose;

	/**
	 * 
	 */
	Map<String, NdType> typesBySerialName;
	
	
	Map<String, NdType> typesByRuntimeName;
	
	
	/**
	 * Note that types length is not equal to nTypes
	 */
	NdType[] types;
 	
	
	/**
	 * 
	 */
	int nTypes;
	
	int highestTypeCode = 0;
	

	/**
	 * 
	 * @param typesContext
	 */
	public NdCodebase(boolean isVerbose) {
		super();
		
		this.isVerbose = isVerbose;
		
		typesBySerialName = new HashMap<>();
		
		typesByRuntimeName = new HashMap<>();
		
	}

	
	public boolean isVerbose() {
		return isVerbose;
	}
	
	void put(NdType type){
		
		
		typesBySerialName.put(type.getSerialName(), type);
		
		typesByRuntimeName.put(type.getBaseType().getName(), type);
	}

	
	/**
	 * (Persistency side)
	 * @param name
	 * @return
	 */
	public NdType getTypeBySerialName(String name) {
		return typesBySerialName.get(name);
	}
	
	
	/**
	 * (Runtime side)
	 * @param name
	 * @return
	 */
	public NdType getTypeByRuntimeName(String name) {
		return typesByRuntimeName.get(name);
	}
	
	/**
	 * (Runtime side)
	 * @param outboundTypeName
	 * @return
	 */
	public NdType getTypeByCode(int code) {
		return types[code];
	}

	/**
	 * Traverse all types
	 * (Compilation time only)
	 * @param consumer
	 */
	public void forEachObjectType(BiConsumer<String, NdType> consumer) {
		typesBySerialName.forEach(consumer);
	}
	



	public boolean isTypeKnown(Class<?> type) {
		return typesByRuntimeName.containsKey(type.getName());
	}

	/**
	 * 
	 * @param prototype
	 * @return
	 * @throws IOException 
	 */
	public NdType getType(Object object) {
		return typesByRuntimeName.get(object.getClass().getName());
	}


	public void DEBUG_print() {
		typesBySerialName.forEach((name, type) -> type.DEBUG_print(""));
	}
	
	
}
