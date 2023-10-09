package com.s8.io.bohr.neon.core;

import java.util.HashMap;
import java.util.Map;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.WebS8Vertex;
import com.s8.io.bytes.base64.Base64Composer;

/**
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeBranch implements WebS8Session {
	
	
	public final String id;
	
	
	
	final Map<String, NeObjectTypeHandler> prototypesByName;
	
	final Map<Long, NeObjectTypeHandler> prototypesByCode;
	
	
	/**
	 * 
	 */
	final Map<String, NeVertex0> vertices;

	
	/**
	 * 
	 */
	public long highestObjectId = 0x02L;
	
	public long highestTypeCode = 0x02L;
	
	
	
	public final NeInbound inbound;
	
	public final NeOutbound outbound;
	
	
	private final Base64Composer idxGen;
	
	public NeBranch(String id) {
		super();
		this.id = id;
		
		prototypesByName = new HashMap<>();
		prototypesByCode = new HashMap<>();
		vertices = new HashMap<>();
		
		
		/* outbound */
		this.inbound = new NeInbound(this);
		this.outbound = new NeOutbound();
		
		idxGen = new Base64Composer(id);
	}



	/**
	 * 
	 * @return
	 */
	public String createNewIndex() {
		return idxGen.generate(++highestObjectId);
	}
	

	
	
	/**
	 * 
	 * @param vertex
	 * @return
	 */
	String appendObject(NeVertex0 object) {
		
		String index = createNewIndex();
		
		vertices.put(index, object);
		
		outbound.notifyChanged(object);
		
		return index;
	}
	
	

	/**
	 * 
	 * @param index
	 * @return
	 */
	public WebS8Vertex getVertex(String index) {
		return vertices.get(index);
	}
	
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public WebS8Object getObject(String index) {
		WebS8Vertex vertex = vertices.get(index);
		return vertex != null ? vertex.getAttachedObject() : null;
	}



	/**
	 * 
	 * @param typename
	 * @return
	 */
	public NeObjectTypeHandler retrieveObjectPrototype(String typename) {
		return prototypesByName.computeIfAbsent(typename, name -> {
			NeObjectTypeHandler proto = new NeObjectTypeHandler(name, highestTypeCode++);
			
			// store by code (so share code with front)
			prototypesByCode.put(proto.code, proto);
			return proto;
		});
	}



	@Override
	public WebS8Vertex createVertex(String typeName, WebS8Object object) {
		return new NeVertex0(this, typeName, object);
	}

}
