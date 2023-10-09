package com.s8.arch.silicon;


/**
 * Immutable object
 * @author pierreconvert
 *
 */
public final class SiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6475976382658157697L;

	
	public final int code;
	
	public SiException(int code, String message) {
		super(message);
		this.code = code;
	}
	
}
