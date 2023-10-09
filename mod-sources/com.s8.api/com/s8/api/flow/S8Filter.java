package com.s8.api.flow;

@FunctionalInterface
public interface S8Filter<T> {

	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public boolean isSelected(T object);
	
}
