package com.s8.api.flow.outputs;

import com.s8.api.flow.S8User;

/**
 * 
 * @author pierreconvert
 *
 */
public class GetUserS8AsyncOutput extends S8AsyncOutput {

	
	
	public boolean isUserDefined = false;
	
	/**
	 * 
	 */
	public S8User user;
	
	
	/**
	 * 
	 * @param isSuccessful
	 * @param hasIOException
	 * @param user
	 */
	public GetUserS8AsyncOutput() {
		super();
	}

	
	public void setUser(S8User user) {
		this.user = user;
		this.isSuccessful = true;
		this.isUserDefined = user != null;
	}
	
}
