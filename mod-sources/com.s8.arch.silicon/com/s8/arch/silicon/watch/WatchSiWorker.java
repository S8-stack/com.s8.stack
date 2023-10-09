package com.s8.arch.silicon.watch;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * @author pierreconvert
 *
 */
public class WatchSiWorker {
	
	public final int id;
	
	private final boolean isVerbose;

	Thread thread;

	AtomicBoolean isProcessing;

	AtomicBoolean isRunning;

	private volatile Process process;

	WatchSiTask task;

	public WatchSiWorker(int id, boolean isVerbose) {
		this.id = id;
		this.isVerbose = isVerbose;
		isRunning = new AtomicBoolean(false);
		isProcessing = new AtomicBoolean(false);
	}


	private void start() {
		if(isRunning.compareAndSet(false, true)) {

			process = new Process();
			
			thread = new Thread(process);

			thread.start();
		}
	}


	private class Process implements Runnable {

		@Override
		public void run() {
			while(isRunning.get()) {
				/**
				 * FROM JAVADOC: 
				 * 
				 * The current thread must own this object's monitor. The thread releases
				 * ownership of this monitor and waits until another thread notifies threads
				 * waiting on this object's monitor to wake up either through a call to the
				 * notify method or the notifyAll method. The thread then waits until it can
				 * re-obtain ownership of the monitor and resumes execution.
				 * 
				 */
				synchronized (this) {

					/*
					 * protection against spurious wakeup of the wait command inside
					 */
					while(task==null) {
						try {
							// wait to have some payload
							Process.this.wait();
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}


				/* run the task and replace current trailing task */
				task = task.run();

				/* if new task has been set, then skip releasing this worker */
				if(task==null) {
					// notify that is now ready for another job
					isProcessing.set(false);	
				}
			}
		}
	}


	/**
	 * 
	 * @param task
	 * @return
	 */
	public boolean submit(WatchSiTask task) {

		if(isProcessing.compareAndSet(false, true)) {


			// start process if not already done
			start();
			
			/**
			 * The awakened thread will not be able to proceed until the current thread
			 * relinquishes the lock on this object. The awakened thread will compete in the
			 * usual manner with any other threads that might be actively competing to
			 * synchronize on this object; for example, the awakened thread enjoys no
			 * reliable privilege or disadvantage in being the next thread to lock this
			 * object.
			 * 
			 * This method should only be called by a thread that is the owner of this
			 * object's monitor. A thread becomes the owner of the object's monitor in one
			 * of three ways:
			 */
			synchronized(process) {

				// set the task
				this.task = task;

				// notify process to wake up
				process.notify();
			}

			// job has indeed been taken by worker
			return true;
		}
		else {
			if(isVerbose) {
				System.out.println("Currently running: "+this.task.describe()+", so cannot accept: "+task.describe());
			}
			return false;
		}
	}
	
	public void stop() {
		if(isRunning.compareAndSet(true, false)) {
			
			submit(new WatchSiTask() {
				public @Override WatchSiTask run() { return null; }
				public @Override String describe() { return "WORKER_EXHAUSTER"; }
			});
		}
	}

}
