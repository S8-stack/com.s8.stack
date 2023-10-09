package com.s8.arch.magnesium.databases.repository.entry;

import com.s8.arch.magnesium.callbacks.ExceptionMgCallback;
import com.s8.arch.silicon.async.AsyncSiTask;

/**
 * 
 * @author pierreconvert
 *
 */
public abstract class MgRepositoryOperation {

	
	/**
	 * 
	 */
	public final MgRepositoryHandler handler;
	
	
	/**
	 * 
	 */
	public final ExceptionMgCallback onFailed;

	
	/**
	 * 
	 * @param handler
	 * @param onFailed
	 */
	public MgRepositoryOperation(MgRepositoryHandler handler, ExceptionMgCallback onFailed) {
		super();
		this.handler = handler;
		this.onFailed = onFailed;
	}


	/**
	 * 
	 * @return
	 */
	public abstract AsyncSiTask createTask();
	
}
