package com.s8.arch.silicon.clock;

import com.s8.arch.silicon.SiliconEngine;
import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_Type;


@XML_Type(name = "T3UnitConfig")	
public class ClockSiModuleConfig {

	private long tick = 250;
	
	@XML_SetAttribute(name = "tick")
	public void setTick(long tick) {
		this.tick = tick;
	}

	public ClockSiModuleConfig() {
		super();
	}

	public ClockSiModule build(SiliconEngine engine) {
		return new ClockSiModule(engine, tick);
	}


	/**
	 * 
	 * @return
	 */
	public static ClockSiModuleConfig createDefault() {
		ClockSiModuleConfig config = new ClockSiModuleConfig();
		return config;
	}
}
