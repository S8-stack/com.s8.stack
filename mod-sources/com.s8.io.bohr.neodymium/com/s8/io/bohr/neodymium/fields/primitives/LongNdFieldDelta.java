package com.s8.io.bohr.neodymium.fields.primitives;

import com.s8.api.bytes.MemoryFootprint;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.type.BuildScope;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class LongNdFieldDelta extends NdFieldDelta {


	public final LongNdField field;

	public final long value;

	public LongNdFieldDelta(LongNdField field, long value) {
		super();
		this.field = field;
		this.value = value;
	}

	public @Override LongNdField getField() { return field; }

	@Override
	public void consume(RepoS8Object object, BuildScope scope) throws NdIOException {
		field.handler.setLong(object, value);
	}

	@Override
	public void computeFootprint(MemoryFootprint weight) {
		weight.reportBytes(8);
	}
}

