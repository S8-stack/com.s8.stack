package com.s8.io.bohr.neodymium.fields.arrays;

import com.s8.api.bytes.MemoryFootprint;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdField;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.type.BuildScope;

/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class IntegerArrayNdFieldDelta extends NdFieldDelta {

	
	public final IntegerArrayNdField field;

	public final int[] value;

	public IntegerArrayNdFieldDelta(IntegerArrayNdField field, int[] array) {
		super();
		this.field = field;
		this.value = array;
	}

	public @Override NdField getField() { return field; }

	@Override
	public void consume(RepoS8Object object, BuildScope scope) throws NdIOException {
		field.handler.set(object, value);
	}


	@Override
	public void computeFootprint(MemoryFootprint weight) {
		if(value!=null) {
			weight.reportInstance();
			weight.reportBytes(value.length*4);
		}
	}

}
