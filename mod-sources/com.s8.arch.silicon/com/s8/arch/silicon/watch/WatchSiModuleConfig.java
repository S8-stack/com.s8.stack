package com.s8.arch.silicon.watch;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_Type;


@XML_Type(name = "T2UnitConfig")	
public class WatchSiModuleConfig {

	/**
	 * 
	 */
	public int nThreads = 4;

	
	private boolean isVerbose = false;
	
	@XML_SetAttribute(name = "n-threads")
	public void setNbThreads(int nThreads) {
		this.nThreads = nThreads;
	}
	
	@XML_SetAttribute(name = "isVerbose")
	public void setVerbosity(boolean isVerbose) {
		this.isVerbose = isVerbose;
	}

	public WatchSiModuleConfig() {
		super();
	}

	public WatchSiModule build() {
		return new WatchSiModule(nThreads, isVerbose);
	}


	/**
	 * 
	 * @return
	 */
	public static WatchSiModuleConfig createDefault() {
		WatchSiModuleConfig config = new WatchSiModuleConfig();
		config.nThreads = 64;
		return config;
	}
}
