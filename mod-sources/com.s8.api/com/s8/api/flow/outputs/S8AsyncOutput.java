package com.s8.api.flow.outputs;

public class S8AsyncOutput {
	
	public boolean isSuccessful = false;
	
	public boolean hasException;
	
	public Exception exception;

	
	public S8AsyncOutput() {
		super();
	}
	
	
	
	
	/**
	 * 
	 * @param exception
	 */
	public void reportException(Exception exception) {
		this.isSuccessful = false;
		this.exception = exception;
		this.hasException = true;
	}

	

}
