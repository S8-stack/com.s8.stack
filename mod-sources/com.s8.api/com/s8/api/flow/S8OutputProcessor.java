package com.s8.api.flow;


/**
 * 
 * @author pierreconvert
 *
 */
public interface S8OutputProcessor<T> {

	
	/**
	 * 
	 * @throws Exception
	 */
	public void run(T arg);
	
}
