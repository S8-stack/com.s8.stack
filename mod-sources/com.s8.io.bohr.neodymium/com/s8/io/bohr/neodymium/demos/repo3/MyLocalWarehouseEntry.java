package com.s8.io.bohr.neodymium.demos.repo3;

import com.s8.api.objects.annotations.S8Field;
import com.s8.api.objects.annotations.S8RowType;
import com.s8.io.bohr.atom.S8Row;
import com.s8.io.bohr.neodymium.object.NdRef;

@S8RowType(name = "warehouse-entry")
public final class MyLocalWarehouseEntry extends S8Row {

	public MyLocalWarehouseEntry(String index) {
		super(index);
	}

	public @S8Field(name = "product-name") String name;
	
	public @S8Field(name = "quantity") int quantity;
	
	public @S8Field(name = "product") NdRef<MyProduct> product;
	
}
