package com.s8.arch.silicon.async;

/**
 * 
 * @author pierreconvert
 *
 */
public enum MthProfile {
	

	UNDEFINED(0x00, 1),

	WEB_REQUEST_SELECTING(0x22, 1),

	WEB_REQUEST_PROCESSING(0x24, 1),
	
	
	FILE_WATCHING(0x32, 8),
	
	IO_DATA_LAKE(0x42, 8),

	IO_SSD(0x44, 4),

	IO_LOCAL_DISK(0x46, 4),

	
	
	S8SHELL_SCAN(0x52, 4),

	
	
	/**
	 * Almost instantly milliseconds (typically < 2ms) 
	 * NON-BLOCKING
	 */
	FX0(0x60, 2),

	/**
	 * Few milliseconds (typically < 16ms)
	 * NON-BLOCKING
	 */
	FX1(0x61, 16),


	/**
	 * Fast but not instantaneous (typically < 128 ms)
	 * NON-BLOCKING
	 */
	FX2(0x62, 128),


	/**
	 * Quite a load (typically < 1024 ms ~ 1s )
	 * ALMOST-BLOCKING
	 */
	FX3(0x63, 1024),


	/**
	 * Long (typically < 8192 ms ~ 8s )
	 * BLOCKING
	 */
	FX4(0x64, 8192),

	/**
	 * Cause server blocking of not handled correctly (typically < 65536 ms ~ 1min )
	 * BLOCKING
	 */
	FX5(0x65, 65536),

	/**
	 * Cause server blocking of not handled correctly (typically < 524288 ms ~ 8min )
	 * Server STUCKED when not handled specifically
	 */
	FX6(0x66, 524288),

	/**
	 * Cause server blocking if not handled correctly (typically < 4194304 ms ~ 1h )
	 * Server STUCKED when not handled specifically
	 */
	FX7(0x67, 4194304);

	

	
	/**
	 * 
	 */
	public final static int CODE_RANGE = 0xff+1;
	
	public short code;

	public long latency;

	private MthProfile(int code, long latency) {
		this.code = (short) code;
		this.latency = latency;
	}	


}
