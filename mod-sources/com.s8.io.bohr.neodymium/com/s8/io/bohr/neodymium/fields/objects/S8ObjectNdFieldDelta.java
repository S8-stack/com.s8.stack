package com.s8.io.bohr.neodymium.fields.objects;

import com.s8.api.bytes.MemoryFootprint;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdFieldDelta;
import com.s8.io.bohr.neodymium.type.BuildScope;
import com.s8.io.bohr.neodymium.type.BuildScope.Binding;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class S8ObjectNdFieldDelta extends NdFieldDelta {



	public final S8ObjectNdField field;

	/**
	 * 
	 */
	public final String index;


	/**
	 * 
	 * @param fieldCode
	 * @param field
	 * @param index
	 */
	public S8ObjectNdFieldDelta(S8ObjectNdField field, String index) {
		super();
		this.field = field;
		this.index = index;
	}


	@Override
	public void consume(RepoS8Object object, BuildScope scope) throws NdIOException {

		if(index != null) {
			scope.appendBinding(new Binding() {

				@Override
				public void resolve(BuildScope scope) throws NdIOException {
					RepoS8Object struct = scope.retrieveObject(index);
					field.handler.set(object, struct);
				}
			});
		}
		else {
			// nothing to do
			field.handler.set(object, null);
		}
	}


	@Override
	public S8ObjectNdField getField() { 
		return field;
	}

	@Override
	public void computeFootprint(MemoryFootprint weight) {
		weight.reportReference();
	}

}
