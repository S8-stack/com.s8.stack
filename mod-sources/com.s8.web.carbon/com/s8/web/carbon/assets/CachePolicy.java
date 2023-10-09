package com.s8.web.carbon.assets;


/**
 * 
 * @author pierreconvert
 *
 */
public enum CachePolicy {
	
	
	
	/**
	 * L'en-tête HTTP `Cache-Control: max-age=2592000` indique alors que la ressource est valide sur une durée de 2592000 secondes (soit environ 1 mois)
	 */
	STABLE("public, max-age=2592000"),
	
	
	/**
	 * reload everytime requested (so can reflect latest change)
	 */
	DEBUG("no-cache"),
	

	/**
	 * load once, store in RAM and serve 
	 */
	PRODUCTION("public, max-age=2592000");
	
	
	
	
	public final String value;

	
	/**
	 * 
	 * @param value
	 */
	private CachePolicy(String value) {
		this.value = value;
	}
	
	
	
	
}
