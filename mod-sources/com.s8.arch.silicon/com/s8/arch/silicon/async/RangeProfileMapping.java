package com.s8.arch.silicon.async;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class RangeProfileMapping extends ProfileMapping {

	
	private final int[] slots;

	private final AtomicInteger fork;

	private final int nSlots;

	private final IntUnaryOperator udpater;

	public RangeProfileMapping(MthProfile profile, int[] slots) {
		super(profile);
		this.slots = slots;
		this.nSlots = slots.length;

		fork = new AtomicInteger();
		udpater = new IntUnaryOperator() {

			@Override
			public int applyAsInt(int previous) {
				if(previous<nSlots-1) {
					return previous+1;	
				}
				else {
					return 0;
				}
			}
		};
	}


	public boolean isValid() {
		return slots.length>0;
	}


	@Override
	public int getSlot() {
		return slots[fork.getAndUpdate(udpater)];
	}

}
