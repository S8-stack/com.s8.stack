package com.s8.io.bohr.neodymium.fields.primitives;

import java.io.IOException;
import java.io.Writer;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.bytes.MemoryFootprint;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.io.bohr.neodymium.exceptions.NdBuildException;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdField;
import com.s8.io.bohr.neodymium.fields.NdFieldComposer;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.fields.NdFieldParser;
import com.s8.io.bohr.neodymium.fields.NdFieldPrototype;
import com.s8.io.bohr.neodymium.handlers.NdHandler;
import com.s8.io.bohr.neodymium.properties.NdFieldProperties;
import com.s8.io.bohr.neodymium.type.BuildScope;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class StringNdField extends PrimitiveNdField {


	public final static StringNdField.Prototype PROTOTYPE = new Prototype(String.class){

		@Override
		public PrimitiveNdField.Builder createFieldBuilder(NdFieldProperties properties, NdHandler handler) {
			return new StringNdField.Builder(properties, handler);
		}
	};


	private static class Builder extends PrimitiveNdField.Builder {

		public Builder(NdFieldProperties properties, NdHandler handler) {
			super(properties, handler);
		}

		@Override
		public NdFieldPrototype getPrototype() {
			return PROTOTYPE;
		}

		@Override
		public NdField build(int ordinal) throws NdBuildException {
			return new StringNdField(ordinal, properties, handler);
		}		
	}


	/**
	 * 
	 * @param outboundTypeName
	 * @param handler
	 * @throws NdBuildException 
	 */
	public StringNdField(int ordinal, NdFieldProperties properties, NdHandler handler) throws NdBuildException{
		super(ordinal, properties, handler);
	}
	

	@Override
	public Prototype getPrototype() {
		return PROTOTYPE;
	}

	
	
	@Override
	public StringNdFieldDelta produceDiff(RepoS8Object object) throws NdIOException {
		return new StringNdFieldDelta(this, handler.getString(object));
	}


	@Override
	public void computeFootprint(RepoS8Object object, MemoryFootprint weight) throws NdIOException {
		String value = handler.getString(object);
		if(value!=null) {
			weight.reportInstance();
			weight.reportBytes(value.length());
		}
	}

	@Override
	public void deepClone(RepoS8Object origin, RepoS8Object clone, BuildScope scope) throws NdIOException {
		String value = handler.getString(origin);
		handler.setString(clone, value);
	}


	@Override
	public boolean hasDiff(RepoS8Object base, RepoS8Object update) throws NdIOException {
		String baseValue = handler.getString(base);
		String updateValue = handler.getString(update);
		if(baseValue==null && updateValue==null) {
			return false;
		}
		else if((baseValue!=null && updateValue==null) || (baseValue==null && updateValue!=null)) {
			return true;
		}
		else {
			return !baseValue.equals(updateValue);	
		}
	}


	@Override
	public void DEBUG_print(String indent) {
		System.out.println(indent+name+": (String)");
	}



	@Override
	protected void printValue(RepoS8Object object, Writer writer) throws IOException {
		String val = handler.getString(object);
		writer.write(val!=null ? val : "<null>");
	}






	/* <IO-inflow-section> */


	@Override
	public NdFieldParser createParser(ByteInflow inflow) throws IOException {
		int code = inflow.getUInt8();
		switch(code) {

		case BOHR_Types.STRING_UTF8 : return new UTF8Parser();

		default : throw new NdIOException("Failed to find field-inflow for code: "+Integer.toHexString(code));
		}
	}



	private class UTF8Parser extends NdFieldParser {

		@Override
		public StringNdField getField() {
			return StringNdField.this;
		}


		@Override
		public NdFieldDelta deserializeDelta(ByteInflow inflow) throws IOException {
			return new StringNdFieldDelta(StringNdField.this, inflow.getStringUTF8());
		}


	}

	/* </IO-inflow-section> */


	/* <IO-outflow-section> */

	public NdFieldComposer createComposer(int code) throws NdIOException {
		switch(exportFormat) {
		case DEFAULT_FLOW_TAG:
		case "StringUTF8" : return new UTF8Composer(code);
		default : throw new NdIOException("Failed to find field-outflow for encoding: "+exportFormat);
		}
	}


	private class UTF8Composer extends NdFieldComposer {

		public UTF8Composer(int code) {
			super(code);
		}

		@Override
		public StringNdField getField() {
			return StringNdField.this;
		}

		@Override
		public void publishFlowEncoding(ByteOutflow outflow) throws IOException {
			outflow.putUInt8(BOHR_Types.STRING_UTF8);
		}

		@Override
		public void composeValue(RepoS8Object object, ByteOutflow outflow) throws IOException {
			outflow.putStringUTF8(handler.getString(object));
		}

		@Override
		public void publishValue(NdFieldDelta delta, ByteOutflow outflow) throws IOException {
			outflow.putStringUTF8(((StringNdFieldDelta) delta).value);	
		}
	}


	/* <IO-outflow-section> */	


}
