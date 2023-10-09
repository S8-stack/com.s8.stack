package com.s8.arch.silicon.watch;

/**
 * 
 * @author pierreconvert
 *
 */
public class WatchSiModule {

	public static final int DEFAULT_CAPACITY = 8;


	private final boolean isVerbose;

	private final int nThreads;


	private final WatchSiWorker[] workers;


	public WatchSiModule(int nThreads, boolean isVerbose) {
		super();
		this.nThreads = nThreads;
		this.isVerbose = isVerbose;

		workers = new WatchSiWorker[nThreads];
		for(int i=0; i<nThreads; i++) {
			workers[i] = new WatchSiWorker(i, isVerbose);
		}
	}




	public void start() {

	}



	public boolean pushTask(WatchSiTask task) {
		if(task!=null) {
			if(isVerbose) {
				System.out.println("\t Start looking for workers, for task="+task.describe());
			}
			
			boolean isAccepted = false;
			int i = 0;
			while(!isAccepted && i<nThreads) {
				isAccepted = workers[i++].submit(task);
			}

			return isAccepted;
		}
		else {
			return true;
		}
	}

	
	
	/**
	 * 
	 */
	public void stop() {
		for(int i=0; i<nThreads; i++) {
			workers[i].stop();
		}
	}





}
