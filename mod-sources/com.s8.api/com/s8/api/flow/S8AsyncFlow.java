package com.s8.api.flow;

import com.s8.api.bytes.Bool64;
import com.s8.api.flow.delivery.S8WebResourceGenerator;
import com.s8.api.flow.outputs.BranchCreationS8AsyncOutput;
import com.s8.api.flow.outputs.BranchExposureS8AsyncOutput;
import com.s8.api.flow.outputs.BranchVersionS8AsyncOutput;
import com.s8.api.flow.outputs.GetUserS8AsyncOutput;
import com.s8.api.flow.outputs.ObjectsListS8AsyncOutput;
import com.s8.api.flow.outputs.PutUserS8AsyncOutput;
import com.s8.api.flow.outputs.RepoCreationS8AsyncOutput;
import com.s8.api.flow.outputs.RepositoryMetadataS8AsyncOutput;
import com.s8.api.flow.outputs.SpaceExposureS8AsyncOutput;
import com.s8.api.flow.outputs.SpaceVersionS8AsyncOutput;


/**
 * 
 * @author pierreconvert
 * Copyright (c) 2022, Pierre Convert. All rights reserved.
 *
 */
public interface S8AsyncFlow {
	
	
	public final static long CREATE_SPACE_IF_NOT_PRESENT = Bool64.BIT02;
	
	public final static long SAVE_IMMEDIATELY_AFTER = Bool64.BIT03;
	
	public final static long SHOULD_NOT_OVERRIDE = Bool64.BIT04;
	
	public final static long HEAD_VERSION = -0x62L;


	public abstract S8User getMe();



	/**
	 * 
	 * @return
	 */
	public default String getMySpaceId() {
		return getMe().getPersonalSpaceId();
	}





	/**
	 * 
	 * @param profile
	 * @param runnable
	 */
	public abstract S8AsyncFlow runBlock(int force, S8CodeBlock runnable);



	/**
	 * 
	 * @param username
	 * @param user
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow getUser(String username, 
			S8OutputProcessor<GetUserS8AsyncOutput> user, long options);
	
	
	/**
	 * 
	 * @param username
	 * @param user
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow getUser(String username, S8OutputProcessor<GetUserS8AsyncOutput> user) {
		return getUser(username, user, 0x0L);
	}
	
	

	/**
	 * 
	 * @param username
	 * @param user
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow putUser(S8User user,
			S8OutputProcessor<PutUserS8AsyncOutput> onInserted, long options);
	
	
	/**
	 * 
	 * @param username
	 * @param user
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow putUser(S8User user, S8OutputProcessor<PutUserS8AsyncOutput> onInserted) {
		return putUser(user, onInserted, 0x0L);
	}


	
	/**
	 * 
	 * @param user
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow selectUsers(S8Filter<S8User> filter, 
			S8OutputProcessor<ObjectsListS8AsyncOutput<S8User>> onSelected, long options);
	
	
	/**
	 * 
	 * @param user
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow selectUsers(S8Filter<S8User> filter, 
			S8OutputProcessor<ObjectsListS8AsyncOutput<S8User>> onSelected) {
		return selectUsers(filter, onSelected, 0x0L);
	}


	/**
	 * 
	 * @param workspaceId
	 * @param onAccessed
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow accessMySpace(S8OutputProcessor<SpaceExposureS8AsyncOutput> onAccessed, long options);
	
	
	/**
	 * 
	 * @param workspaceId
	 * @param onAccessed
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow accessMySpace(S8OutputProcessor<SpaceExposureS8AsyncOutput> onAccessed) {
		return accessMySpace(onAccessed, 0x0L);
	}


	
	
	
	/**
	 * 
	 * @param exposure
	 * @param onRebased
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow exposeMySpace(Object[] exposure, 
			S8OutputProcessor<SpaceVersionS8AsyncOutput> onRebased, 
			long options);
	
	
	/**
	 * 
	 * @param exposure
	 * @param onRebased
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow exposeMySpace(Object[] exposure, 
			S8OutputProcessor<SpaceVersionS8AsyncOutput> onRebased) {
		return exposeMySpace(exposure, onRebased, 0x0L);
	}



	/**
	 * 
	 * @param spaceId
	 * @param onAccessed
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow accessSpace(String spaceId, 
			S8OutputProcessor<SpaceExposureS8AsyncOutput> onAccessed, long options);
	
	
	
	/**
	 * 
	 * @param spaceId
	 * @param onAccessed
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow accessSpace(String spaceId, 
			S8OutputProcessor<SpaceExposureS8AsyncOutput> onAccessed) {
		return accessSpace(spaceId, onAccessed, 0x0L);
	}

	

	/**
	 * 
	 * @param exposure
	 * @param onRebased
	 * @param onException
	 * @return 
	 */
	public abstract S8AsyncFlow exposeSpace(String spaceId, Object[] exposure, 
			S8OutputProcessor<SpaceVersionS8AsyncOutput> onRebased, 
			long options);


	/**
	 * 
	 * @param exposure
	 * @param onRebased
	 * @param onException
	 * @return 
	 */
	public default S8AsyncFlow exposeSpace(String spaceId, Object[] exposure, 
			S8OutputProcessor<SpaceVersionS8AsyncOutput> onRebased) {
		return exposeMySpace(exposure, onRebased, 0x0L);
	}
	
	



	/**
	 * Note that:
	 * - initiator will become owner of the repository
	 * - initiator will become owner of the main branch
	 * @param pre
	 * @param post
	 * @return 
	 */
	public abstract S8AsyncFlow createRepository(
			String repositoryName, 
			String repositoryAddress, 
			String repositoryInfo, 
			String mainBranchName,
			Object[] objects, String initialCommitComment,
			S8OutputProcessor<RepoCreationS8AsyncOutput> onCommitted, long options);
	
	
	/**
	 * 
	 * @param pre
	 * @param post
	 * @return 
	 */
	public abstract S8AsyncFlow getRepositoryMetadata(
			String repositoryAddress, 
			S8OutputProcessor<RepositoryMetadataS8AsyncOutput> onForked, long options);


	/**
	 * 
	 * @param pre
	 * @param post
	 * @return 
	 */
	public abstract S8AsyncFlow forkRepository(
			String originRepositoryAddress, 
			String originBranchId, long originBranchVersion,
			String targetRepositoryAddress, 
			S8OutputProcessor<BranchCreationS8AsyncOutput> onForked, long options);
	
	
	
	/**
	 * 
	 * @param pre
	 * @param post
	 * @return 
	 */
	public abstract S8AsyncFlow forkBranch(String repositoryAddress, 
			String originBranchId, long originBranchVersion, String targetBranchId, 
			S8OutputProcessor<BranchCreationS8AsyncOutput> onForked, long options);
	

	
	
	
	/**
	 * 
	 * @param pre
	 * @param post
	 * @return 
	 */
	public abstract S8AsyncFlow commitBranch(String repositoryAddress, String branchId, 
			Object[] objects, String author, String comment,
			S8OutputProcessor<BranchVersionS8AsyncOutput> onCommitted, long options);
	
	
	/**
	 * 
	 * @param pre
	 * @param post
	 * @return 
	 */
	public default S8AsyncFlow commitBranch(String repositoryAddress, String branchId, 
			Object[] objects, String author, String comment,
			S8OutputProcessor<BranchVersionS8AsyncOutput> onCommitted) {
		return commitBranch(repositoryAddress, branchId, objects, author, comment, onCommitted, 0x0L);
	}


	/**
	 * 
	 * @param pre
	 * @param post
	 */
	public default S8AsyncFlow cloneBranchHead(String repositoryAddress, String branchId, 
			S8OutputProcessor<BranchExposureS8AsyncOutput> onCloned, long options) {
		return cloneBranch(repositoryAddress, branchId, HEAD_VERSION, onCloned, options);
	}
	
	
	/**
	 * 
	 * @param pre
	 * @param post
	 */
	public default S8AsyncFlow cloneBranchHead(String repositoryAddress, String branchId, 
			S8OutputProcessor<BranchExposureS8AsyncOutput> onCloned) {
		return cloneBranch(repositoryAddress, branchId, HEAD_VERSION, onCloned, 0x0L);
	}


	
	/**
	 * 
	 * @param pre
	 * @param post
	 */
	public abstract S8AsyncFlow cloneBranch(String repositoryAddress, String branchId, long version,
			S8OutputProcessor<BranchExposureS8AsyncOutput> onCloned, long options);

	
	/**
	 * 
	 * @param pre
	 * @param post
	 */
	public default S8AsyncFlow cloneBranch(String repositoryAddress, String branchId, long version,
			S8OutputProcessor<BranchExposureS8AsyncOutput> onCloned) {
		return cloneBranch(repositoryAddress, branchId, version, onCloned, 0x0L);
	}



	/**
	 * Send and play 
	 */
	public void send();



	/**
	 * 
	 */
	public void play();
	
	
	

	/**
	 * Deliver a generated resource
	 * 
	 * @param load
	 * @param generator
	 */
	public void deliver(int load, S8WebResourceGenerator generator);

}
