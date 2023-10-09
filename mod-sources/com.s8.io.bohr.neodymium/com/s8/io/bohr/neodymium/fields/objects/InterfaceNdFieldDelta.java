package com.s8.io.bohr.neodymium.fields.objects;

import com.s8.api.bytes.MemoryFootprint;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.fields.NdField;
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
public class InterfaceNdFieldDelta extends NdFieldDelta {

	public final InterfaceNdField field;

	public final String index;

	/**
	 * 
	 * @param fieldCode
	 * @param field
	 * @param index
	 */
	public InterfaceNdFieldDelta(InterfaceNdField field, String index) {
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
					if(struct==null) {
						throw new NdIOException("Failed to retriev vertex");
					}
					field.handler.set(object, struct);
				}
			});
		}
		else {
			// nothing to do
			field.handler.set(object, null);
		}
	}

	public @Override NdField getField() { return field; }

	@Override
	public void computeFootprint(MemoryFootprint weight) {
		weight.reportReference();
	}
}
