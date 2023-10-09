package com.s8.arch.silicon.async;

public class AsyncSiModule {


	private final int nThreads;

	/**
	 * 
	 */
	private final AsyncSiWorker[] workers;

	private final ProfileMapping[] mappings;




	/**
	 * 
	 * @param nThreads
	 * @param capacity
	 */
	public AsyncSiModule(int nThreads, int capacity, ProfileMapping[] rules) {
		super();
		this.nThreads = nThreads;
		workers = new AsyncSiWorker[nThreads];

		for(int slot = 0; slot<nThreads; slot++) {
			workers[slot] = new AsyncSiWorker(slot, capacity);
		}
		
		// set rules
		this.mappings = rules;
	}


	public void start() {
		for(int i=0; i<nThreads; i++) {
			workers[i].start();
		}
	}


	/**
	 * 
	 * @param rules
	 */
	public void setRules(ProfileMapping[] rules) {

		
	}




	/**
	 * 
	 * @param task
	 */
	public boolean pushTask(AsyncSiTask task) {
		if(task!=null) {
			MthProfile profile = task.profile();
			int slot = mappings[profile.code].getSlot();
			return workers[slot].pushTask(task);
		}
		else {
			return true;
		}
	}


	public void stop() {

		int n = workers.length;
		for(int i=0; i<n; i++) {
			workers[i].stop();
		}
	}
}
