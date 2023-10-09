package com.s8.arch.silicon;

import com.s8.arch.silicon.async.AsyncSiTask;
import com.s8.arch.silicon.async.AsyncSiModule;
import com.s8.arch.silicon.clock.ClockSiTask;
import com.s8.arch.silicon.clock.ClockSiModule;
import com.s8.arch.silicon.watch.WatchSiTask;
import com.s8.arch.silicon.watch.WatchSiModule;

/**
 * 
 * @author pierreconvert
 *
 */
public class SiliconEngine {


	final AsyncSiModule asyncUnit;

	/**
	 * 
	 */
	final WatchSiModule watchUnit;

	
	/**
	 * 
	 */
	final ClockSiModule clockUnit;


	/**
	 * 
	 * @param nUnits
	 * @param capacity
	 * @param mappings
	 */
	public SiliconEngine(SiliconConfiguration c) {
		super();
		
		// initialize
		asyncUnit = c.t1UnitConfig.build();
		watchUnit = c.t2UnitConfig.build();
		clockUnit = c.t3UnitConfig.build(this);
	}



	/**
	 * 
	 */
	public void start() {

		/* <start-processing-units> */
		asyncUnit.start();
		watchUnit.start();
		clockUnit.start();
		/* </start-processing-units> */
	}


	
	
	/**
	 * @param task
	 * @return true if task has successfully been submitted
	 */
	public boolean pushAsyncTask(AsyncSiTask task) {
		return asyncUnit.pushTask(task);
	}
	
	
	/**
	 * @param task
	 * @return true if task has successfully been submitted
	 */
	public boolean pushWatchTask(WatchSiTask task) {
		return watchUnit.pushTask(task);
	}
	

	/**
	 * @param task
	 * @return true if task has successfully been submitted
	 */
	public boolean pushClockTask(ClockSiTask task) {
		return clockUnit.pushTask(task);
	}
	
	

	/**
	 * 
	 */
	public void stop() {

		
		/* <stop-processing-units> */
		asyncUnit.stop();
		watchUnit.stop();
		/* </stop-processing-units> */
	}


	public static SiliconEngine startBasic() {

		SiliconConfiguration appConfig = new SiliconConfiguration();
		SiliconEngine app = new SiliconEngine(appConfig);
		app.start();
		return app;

	}



}
