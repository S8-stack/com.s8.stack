package com.s8.io.bohr.neodymium.object;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Keywords;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.bytes.MemoryFootprint;
import com.s8.io.bohr.neodymium.branch.NdGraph;
import com.s8.io.bohr.neodymium.branch.endpoint.NdOutbound;
import com.s8.io.bohr.neodymium.exceptions.NdIOException;
import com.s8.io.bohr.neodymium.type.BuildScope;


/**
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class RemoveNdObjectDelta extends NdObjectDelta {

	public RemoveNdObjectDelta(String index) {
		super(index);
	}

	@Override
	public void consume(NdGraph graph, BuildScope scope) throws NdIOException {
		
	}

	@Override
	public void serialize(NdOutbound outbound, ByteOutflow outflow) throws IOException {
		
		/* remove node */
		outflow.putUInt8(BOHR_Keywords.REMOVE_NODE);

		/* define index */
		outflow.putStringUTF8(index);
	}
	

	@Override
	public void computeFootprint(MemoryFootprint weight) {
		// TODO Auto-generated method stub
		
	}

}
