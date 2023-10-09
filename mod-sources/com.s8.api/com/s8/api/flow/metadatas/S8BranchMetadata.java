package com.s8.api.flow.metadatas;

/**
 * 
 * @author pierreconvert
 *
 */
public interface S8BranchMetadata {

	/**
	 * name == id
	 */
	public String getName();


	/**
	 * 
	 * @return
	 */
	public String getInfo();


	/**
	 * 
	 * @return
	 */
	public long getHeadVersion();


	/**
	 * Origin of branch
	 * @return
	 */
	public String getForkedBranchId();


	/**
	 * 
	 */
	public long getForkedBranchVersion();


	/**
	 * 
	 * @return
	 */
	public String getOwner();
}
