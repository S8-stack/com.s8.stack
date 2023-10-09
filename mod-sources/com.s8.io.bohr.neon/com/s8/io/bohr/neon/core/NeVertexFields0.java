package com.s8.io.bohr.neon.core;

import java.util.ArrayList;
import java.util.List;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8VertexFields;
import com.s8.io.bohr.neon.fields.NeFieldHandler;
import com.s8.io.bohr.neon.fields.NeFieldValue;
import com.s8.io.bohr.neon.fields.arrays.Bool8ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.Float32ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.Float64ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.Int64ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.StringUTF8ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.UInt16ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.UInt32ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.arrays.UInt8ArrayNeFieldHandler;
import com.s8.io.bohr.neon.fields.objects.ListNeFieldHandler;
import com.s8.io.bohr.neon.fields.objects.ObjNeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Bool8NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Float32NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Float64NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Int16NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Int32NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Int64NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.Int8NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.StringUTF8NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.UInt16NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.UInt32NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.UInt64NeFieldHandler;
import com.s8.io.bohr.neon.fields.primitives.UInt8NeFieldHandler;


/**
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
public class NeVertexFields0 implements WebS8VertexFields {

	public final NeVertex0 vertex;

	public final NeObjectTypeFields prototype;
	
	NeFieldValue[] values;

	/**
	 * 
	 * @param branch
	 */
	public NeVertexFields0(NeVertex0 vertex, NeObjectTypeFields prototype) {
		super();


		// branch
		this.vertex = vertex;
		this.prototype = prototype;
		
		values = new NeFieldValue[4];

	}



	protected NeFieldValue getEntry(NeFieldHandler field) {
		int ordinal= field.ordinal;
		NeFieldValue value;
		if(field.ordinal < values.length) {
			if((value = values[ordinal]) != null) {
				return value;
			}
			else {
				return (values[ordinal] = field.createValue());
			}
		}
		else {
			// increase values size
			int n = values.length, m = n >= 2 ? n : 2;
			while(m <= field.ordinal) { m*=2; }
			NeFieldValue[] extendedValues = new NeFieldValue[m];
			for(int i = 0; i < n; i++) { extendedValues[i] = values[i]; }
			values = extendedValues;

			return (values[ordinal] = field.createValue());
		}
	}


	

	/* <setters> */


	@Override
	public void setBool8Field(String name, boolean value) {
		Bool8NeFieldHandler field = prototype.getBool8Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setBool8ArrayField(String name, boolean[] value) {
		Bool8ArrayNeFieldHandler field = prototype.getBool8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setUInt8Field(String name, int value) {
		UInt8NeFieldHandler field = prototype.getUInt8Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}

	
	@Override
	public void setUInt8ArrayField(String name, int[] value) {
		UInt8ArrayNeFieldHandler field = prototype.getUInt8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setUInt16Field(String name, int value) {
		UInt16NeFieldHandler field = prototype.getUInt16Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt16ArrayField(String name, int[] value) {
		UInt16ArrayNeFieldHandler field = prototype.getUInt16ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt32Field(String name, long value) {
		UInt32NeFieldHandler field = prototype.getUInt32Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt32ArrayField(String name, long[] value) {
		UInt32ArrayNeFieldHandler field = prototype.getUInt32ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setUInt64Field(String name, long value) {
		UInt64NeFieldHandler field = prototype.getUInt64Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt8Field(String name, int value) {
		Int8NeFieldHandler field = prototype.getInt8Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setInt16Field(String name, int value) {
		Int16NeFieldHandler field = prototype.getInt16Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt32Field(String name, int value) {
		Int32NeFieldHandler field = prototype.getInt32Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt64Field(String name, long value) {
		Int64NeFieldHandler field = prototype.getInt64Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt64ArrayField(String name, long[] value) {
		Int64ArrayNeFieldHandler field = prototype.getInt64ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setFloat32Field(String name, float value) {
		Float32NeFieldHandler field = prototype.getFloat32Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setFloat32ArrayField(String name, float[] value) {
		Float32ArrayNeFieldHandler field = prototype.getFloat32ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setFloat64Field(String name, double value) {
		Float64NeFieldHandler field = prototype.getFloat64Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setFloat64ArrayField(String name, double[] value) {
		Float64ArrayNeFieldHandler field = prototype.getFloat64ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setStringUTF8Field(String name, String value) {
		StringUTF8NeFieldHandler field = prototype.getStringUTF8Field(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public void setStringUTF8ArrayField(String name, String[] value) {
		StringUTF8ArrayNeFieldHandler field = prototype.getStringUTF8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public <T extends WebS8Object> void setObjectField(String name, T value) {
		ObjNeFieldHandler<T> field = prototype.getObjField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public <T extends WebS8Object> void setObjectListField(String name, List<T> value) {
		ListNeFieldHandler<T> field = prototype.getObjArrayField(name);
		NeFieldValue entry = getEntry(field);
		boolean isUpdated = field.set(entry, value);
		if(isUpdated) { vertex.onUpdate(); }
	}


	@Override
	public <T extends WebS8Object> void setObjectListField(String name, T[] value) {
		ListNeFieldHandler<T> field = prototype.getObjArrayField(name);
		NeFieldValue entry = getEntry(field);
		
		/*  transform array into list */
		int n = value.length;
		List<T> list = new ArrayList<>();
		for(int i = 0; i<n; i++) { list.add(value[i]); }
		boolean isUpdated = field.set(entry, list);
		if(isUpdated) { vertex.onUpdate(); }
	}
	

	@Override
	public <T extends WebS8Object> void addObjToList(String name, T obj) {
		ListNeFieldHandler<T> field = prototype.getObjArrayField(name);
		NeFieldValue entry = getEntry(field);
		field.add(entry, obj);
		vertex.onUpdate();
	}


	@Override
	public <T extends WebS8Object> void removeObjFromList(String name, T obj) {
		if(obj != null) {
			ListNeFieldHandler<T> field = prototype.getObjArrayField(name);
			NeFieldValue entry = getEntry(field);
			field.remove(entry, obj.vertex.getId());
			vertex.onUpdate();
		}
	}
	
	
	/* </setters> */

	
	
	/* <getters> */
	
	

	@Override
	public boolean getBool8Field(String name) {
		Bool8NeFieldHandler field = prototype.getBool8Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public boolean[] getBool8ArrayField(String name) {
		Bool8ArrayNeFieldHandler field = prototype.getBool8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int getUInt8Field(String name) {
		UInt8NeFieldHandler field = prototype.getUInt8Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int[] getUInt8ArrayField(String name) {
		UInt8ArrayNeFieldHandler field = prototype.getUInt8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}



	@Override
	public int getUInt16Field(String name) {
		UInt16NeFieldHandler field = prototype.getUInt16Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int[] getUInt16ArrayField(String name) {
		UInt16ArrayNeFieldHandler field = prototype.getUInt16ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public long getUInt32Field(String name) {
		UInt32NeFieldHandler field = prototype.getUInt32Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public long[] getUInt32ArrayField(String name) {
		UInt32ArrayNeFieldHandler field = prototype.getUInt32ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public long getUInt64Field(String name) {
		UInt64NeFieldHandler field = prototype.getUInt64Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int getInt8Field(String name) {
		Int8NeFieldHandler field = prototype.getInt8Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int getInt16Field(String name) {
		Int16NeFieldHandler field = prototype.getInt16Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public int getInt32Field(String name) {
		Int32NeFieldHandler field = prototype.getInt32Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public long getInt64Field(String name) {
		Int64NeFieldHandler field = prototype.getInt64Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public long[] getInt64ArrayField(String name) {
		Int64ArrayNeFieldHandler field = prototype.getInt64ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public float getFloat32Field(String name) {
		Float32NeFieldHandler field = prototype.getFloat32Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public float[] getFloat32ArrayField(String name) {
		Float32ArrayNeFieldHandler field = prototype.getFloat32ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public double getFloat64Field(String name) {
		Float64NeFieldHandler field = prototype.getFloat64Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public double[] getFloat64ArrayField(String name) {
		Float64ArrayNeFieldHandler field = prototype.getFloat64ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public String getStringUTF8Field(String name) {
		StringUTF8NeFieldHandler field = prototype.getStringUTF8Field(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}
	

	@Override
	public String[] getStringUTF8ArrayField(String name) {
		StringUTF8ArrayNeFieldHandler field = prototype.getStringUTF8ArrayField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}
	

	@Override
	public <T extends WebS8Object> T getObjectField(String name) {
		ObjNeFieldHandler<T> field = prototype.getObjField(name);
		NeFieldValue entry = getEntry(field);
		return field.get(entry);
	}


	@Override
	public <T extends WebS8Object> List<T> getObjectListField(String name) {
		ListNeFieldHandler<T> field = prototype.getObjArrayField(name);
		NeFieldValue entry = getEntry(field);
		List<T> list = field.get(entry);
		List<T> copy = new ArrayList<T>(list.size());
		list.forEach(item -> copy.add(item));
		return copy;
	}


	/* </getters> */

}
