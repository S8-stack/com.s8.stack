package com.s8.io.bohr.neon.core;

import java.io.IOException;

import com.s8.api.bohr.BOHR_Keywords;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Vertex;
import com.s8.api.objects.web.WebS8VertexFields;
import com.s8.api.objects.web.WebS8VertexMethods;
import com.s8.api.objects.web.WebS8VertexProviders;


/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeVertex0 implements WebS8Vertex {

	public final NeBranch branch;

	public final WebS8Object object;

	private boolean hasUnpublishedChanges = false;

	private boolean isCreateUnpublished = false;

	private boolean isExposeUnpublished = false;

	private boolean isUpdateUnpublished = false;


	private int slot;




	public final NeObjectTypeHandler prototype;


	/**
	 * index
	 */
	private String index;

	
	public final NeVertexFields0 fields;
	
	public final NeVertexMethods0 methods;

	public final NeVertexProviders0 providers;


	/**
	 * 
	 * @param branch
	 */
	public NeVertex0(NeBranch branch, String typeName, WebS8Object object) {
		super();


		// branch
		this.branch = branch;
		this.prototype = branch.retrieveObjectPrototype(typeName);
		this.object = object;

		this.fields = new NeVertexFields0(this, prototype.fields);
		this.methods = new NeVertexMethods0(this, prototype.methods);
		this.providers = new NeVertexProviders0(this, prototype.providers);
	}

	@Override
	public NeBranch getSession() {
		return branch;
	}

	@Override
	public WebS8Object getAttachedObject() {
		return object;
	}
	
	
	/**
	 * 
	 */
	public NeObjectTypeHandler getPrototype() {
		return prototype;
	}


	@Override
	public String getId() {

		if(index == null) {

			index = branch.appendObject(this);

			/* keep track of update required status */
			isCreateUnpublished = true;

			onChange();
		}

		return index;
	}



	@Override
	public void expose(int slot) {

		isExposeUnpublished = true;

		this.slot = slot;

		/* general change notified */
		onChange();
	}




	protected void onUpdate() {

		/* keep track of update required status */
		isUpdateUnpublished = true;

		/* general change notified */
		onChange();
	}


	protected void onChange() {
		if(!hasUnpublishedChanges) {

			/* push toUnpublished */
			branch.outbound.notifyChanged(this);


			/* keep track of unchanged status */
			hasUnpublishedChanges = true;
		}
	}


	@Override
	public void publish(ByteOutflow outflow) throws IOException {

		if(hasUnpublishedChanges) {
			String index = getId();

			/* publish prototype */
			prototype.publish_DECLARE_TYPE(outflow);

			if(isCreateUnpublished) {

				// declare type
				outflow.putUInt8(BOHR_Keywords.CREATE_NODE);

				/* publish type code */
				outflow.putUInt7x(prototype.code);

				/* publish index */
				outflow.putStringUTF8(index);

				prototype.fields.publishFields(fields.values, outflow);

				outflow.putUInt8(BOHR_Keywords.CLOSE_NODE);

				isCreateUnpublished = false;
			}	
			else if(isUpdateUnpublished) {

				// declare type
				outflow.putUInt8(BOHR_Keywords.UPDATE_NODE);

				/* publish index */
				outflow.putStringUTF8(index);

				/* fields */
				prototype.fields.publishFields(fields.values, outflow);

				outflow.putUInt8(BOHR_Keywords.CLOSE_NODE);

				isUpdateUnpublished = false;
			}

			if(isExposeUnpublished) {

				// declare type
				outflow.putUInt8(BOHR_Keywords.EXPOSE_NODE);

				/* publish index */
				outflow.putStringUTF8(index);

				/* fields */
				outflow.putUInt8(slot);

				isExposeUnpublished = false;
			}

			hasUnpublishedChanges = false;
		}
	}
	

	@Override
	public WebS8VertexFields fields() {
		return fields;
	}

	@Override
	public WebS8VertexMethods methods() {
		return methods;
	}

	@Override
	public WebS8VertexProviders providers() {
		return providers;
	}





}
