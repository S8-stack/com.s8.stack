package com.s8.arch.magnesium.databases.repository.branch;

import java.io.IOException;

import com.s8.api.flow.S8AsyncFlow;
import com.s8.api.flow.S8User;
import com.s8.api.flow.outputs.BranchCreationS8AsyncOutput;
import com.s8.api.objects.repo.RepoS8Object;
import com.s8.arch.magnesium.callbacks.MgCallback;
import com.s8.arch.magnesium.databases.RequestDbMgOperation;
import com.s8.arch.magnesium.handlers.h3.ConsumeResourceMgAsyncTask;
import com.s8.arch.magnesium.handlers.h3.H3MgHandler;
import com.s8.arch.silicon.async.MthProfile;
import com.s8.io.bohr.atom.S8ShellStructureException;
import com.s8.io.bohr.neodymium.branch.NdBranch;
import com.s8.io.bohr.neodymium.codebase.NdCodebase;

/**
 * 
 * @author pierreconvert
 *
 */
class ForkBranchOp extends RequestDbMgOperation<NdBranch> {


	public final MgBranchHandler originBranchHandler;

	public final long version;
	
	public final MgBranchHandler targetBranchHandler;

	public final MgCallback<BranchCreationS8AsyncOutput> onSucceed;


	/**
	 * 
	 * @param originBranchHandler
	 * @param version
	 * @param onSucceed
	 * @param onFailed
	 */
	public ForkBranchOp(long timestamp, S8User initiator,
			MgBranchHandler originBranchHandler, long version, MgBranchHandler targetBranchHandler,
			MgCallback<BranchCreationS8AsyncOutput> onSucceed, long options) {
		super(timestamp, initiator, options);
		this.originBranchHandler = originBranchHandler;
		this.version = version;
		this.targetBranchHandler = targetBranchHandler;
		this.onSucceed = onSucceed;
	}


	@Override
	public H3MgHandler<NdBranch> getHandler() {
		return originBranchHandler;
	}

	@Override
	public ConsumeResourceMgAsyncTask<NdBranch> createAsyncTask() {
		return new ConsumeResourceMgAsyncTask<NdBranch>(originBranchHandler) {


			@Override
			public MthProfile profile() { 
				return MthProfile.FX0; 
			}

			@Override
			public String describe() {
				return "CLONE-HEAD on "+originBranchHandler.getIdentifier()+" branch of "+originBranchHandler.getName()+ " repository";
			}

			@Override
			public boolean consumeResource(NdBranch branch) throws IOException, S8ShellStructureException {

				/* standard cases */
				RepoS8Object[] objects = null;
				if(version >= 0L) {
					objects = branch.cloneVersion(version).exposure;
				}
				/* special cases */
				else if(version == S8AsyncFlow.HEAD_VERSION){
					objects = branch.cloneHead().exposure;
				}
				
				
				BranchCreationS8AsyncOutput output = new BranchCreationS8AsyncOutput();
				if(objects != null) {
					NdCodebase codebase = originBranchHandler.getStore().getCodebase();
					NdBranch targetBranch = new NdBranch(codebase, targetBranchHandler.getIdentifier());
					
					/* <commit> */
					targetBranch.commit(objects, 
							getTimestamp(), 
							getInitiator().getUsername(), 
							"Initial commit from FORK of "+originBranchHandler.getName());
					
					/* initialize handler with newly created branch */
					targetBranchHandler.initializeResource(targetBranch);
					
					output.isSuccessful = true;
				}
				else {
					output.isSuccessful = false;
				}

				onSucceed.call(output);
				return false;
			}

			@Override
			public void catchException(Exception exception) {
				BranchCreationS8AsyncOutput output = new BranchCreationS8AsyncOutput();
				output.reportException(exception);
				onSucceed.call(output);
			}			
			
		};
	}
	

}
