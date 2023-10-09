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
public class LongArrayNdFieldDelta extends NdFieldDelta {

	public final LongArrayNdField field;
	
	public final long[] value;

	public LongArrayNdFieldDelta(LongArrayNdField field, long[] array) {
		super();
		this.field = field;
		this.value = array;
	}


	@Override
	public NdField getField() { 
		return field;
	}


	@Override
	public void consume(RepoS8Object object, BuildScope scope) throws NdIOException {
		field.handler.set(object, value);
	}


	@Override
	public void computeFootprint(MemoryFootprint weight) {
		if(value!=null) {
			weight.reportInstance(); // the array object itself	
			weight.reportBytes(value.length*8);
		}
	}

}
