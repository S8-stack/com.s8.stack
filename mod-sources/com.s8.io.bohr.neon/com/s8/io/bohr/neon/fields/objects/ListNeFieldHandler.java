package com.s8.io.bohr.neon.fields.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.s8.api.bohr.BOHR_Types;
import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.objects.web.WebS8Object;
import com.s8.io.bohr.neon.core.BuildScope;
import com.s8.io.bohr.neon.core.NeObjectTypeFields;
import com.s8.io.bohr.neon.fields.NeFieldHandler;
import com.s8.io.bohr.neon.fields.NeFieldValue;


/**
 * 
 *
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
public class ListNeFieldHandler<T extends WebS8Object> extends NeFieldHandler {

	public final static long SIGNATURE =  BOHR_Types.ARRAY << 8 & BOHR_Types.S8OBJECT;

	public @Override long getSignature() { return SIGNATURE; }

	/**
	 * 
	 * @param view
	 */
	public ListNeFieldHandler(NeObjectTypeFields prototype, String name) {
		super(prototype, name);
	}


	@Override
	public void publishEncoding(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(BOHR_Types.ARRAY);
		outflow.putUInt8(BOHR_Types.S8OBJECT);
	}



	/**
	 * 
	 * @param values
	 * @return
	 */
	
	public List<T> get(NeFieldValue wrapper) {
		@SuppressWarnings("unchecked")
		Value<T> value = (Value<T>) wrapper; 
		if(value.list == null) {
			List<T> list = new ArrayList<T>();
			value.list = list;
			return list;
		}
		else {
			List<T> list = value.list;
			List<T> copy = new ArrayList<T>(list.size());
			list.forEach(item -> copy.add(item));
			return copy;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean set(NeFieldValue wrapper, List<T> list) {
		return ((Value<T>) wrapper).setValue(list);
	}




	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void add(NeFieldValue wrapper, T obj) {
		((Value<T>) wrapper).addObject(obj);
	}
	

	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void remove(NeFieldValue wrapper, String index) {
		((Value<T>) wrapper).removeObject(index);
	}



	@Override
	public NeFieldValue createValue() {
		return new Value<>();
	}



	/**
	 * 
	 * @author pierreconvert
	 *
	 */
	public static class Value<T extends WebS8Object> extends NeFieldValue {

		private List<T> list;

		public Value() {
			super();
		}

		public void notifyChange() {
			hasDelta = true;
		}

		
		
		/**
		 * 
		 * @param value
		 * @return
		 */
		private boolean checkIfHasDelta(List<T> value) {
			if(this.list == null && value == null) {
				return false;
			}
			else if((this.list != null && value == null) || (this.list == null && value != null)) {
				return true;
			}
			else { /* this.value != null && value != null */
				int nLeft = this.list.size(), nRight = value.size();
				if(nLeft != nRight) {
					return true;
				}
				else {
					for(int i= 0; i<nLeft; i++) {
						T left = this.list.get(i), right = value.get(i);
						if((left == null && right != null) || 
								(left != null && right == null) ||
								(left != null && right != null && !left.vertex.getId().equals(right.vertex.getId()))) {
							return true;
						}
					}
					return false;
				}
			}
		}
		
		/**
		 * 
		 * @param value
		 */
		public boolean setValue(List<T> value) {
			if(checkIfHasDelta(value)) {
				this.list = value;
				this.hasDelta = true;
				return true;
			}
			else {
				return false;
			}
		}
		
		
		public void addObject(T obj) {
			if(list == null) { list = new ArrayList<T>(); }
			list.add(obj);
			hasDelta = true;
		}
		
		public void removeObject(String objectIndex) {
			if(list != null) {
				boolean isFound = false;
				int i = 0, n = list.size();
				while(!isFound && i < n) {
					if(list.get(i).vertex.getId().equals(objectIndex)) {
						isFound = true;
					}
					else {
						i++;
					}
				}
				list.remove(i);
				
				hasDelta = true;		
			}
		}

		@Override
		public void compose(ByteOutflow outflow) throws IOException {
			if(list != null) {
				int n = list.size();
				outflow.putUInt7x(n);
				T item;
				for(int i = 0; i < n; i++) {
					item = list.get(i);
					outflow.putStringUTF8(item != null ? item.vertex.getId() : null);
				}
			}
			else {
				outflow.putUInt7x(-1);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void parse(ByteInflow inflow, BuildScope scope) throws IOException {
			int n = (int) inflow.getUInt7x();
			if(n >= 0) {
				List<String> indices = new ArrayList<String>(n);
				for(int i = 0; i < n; i++) { indices.add(inflow.getStringUTF8()); }

				scope.appendBinding(new BuildScope.Binding() {

					@Override
					public void resolve(BuildScope scope) throws IOException {
						list = new ArrayList<T>(n);
						String index;
						for(int i = 0; i < n; i++) {
							index = indices.get(i);
							list.add(index != null ? (T) scope.retrieveObject(indices.get(i)) : null);
						}	
					}
				});
			}
			else {
				list = null;
			}

		}
	}	
}
