package com.s8.api.flow.outputs;

public class BranchCreationS8AsyncOutput extends S8AsyncOutput {

	
	/**
	 * 
	 * @param version
	 * @return
	 */
	public static BranchCreationS8AsyncOutput successful(long version) {
		BranchCreationS8AsyncOutput output = new BranchCreationS8AsyncOutput();
		output.isSuccessful = true;
		output.version = version;
		return output;
	}

	public boolean hasIdConflict = false;
	
	public boolean isRepositoryDoesNotExist = false;
	
	public long version;
	
	
}
