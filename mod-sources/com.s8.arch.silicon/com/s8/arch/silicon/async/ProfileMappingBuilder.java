package com.s8.arch.silicon.async;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_SetValue;
import com.s8.io.xml.annotations.XML_Type;


@XML_Type(name = "ProfileMappingBuilder")
public class ProfileMappingBuilder {

	private MthProfile nature;

	private int currentNbSlots;

	private int[] currentSlots;


	@XML_SetAttribute(name = "profile")
	public void setNature(MthProfile profile) {
		if(profile==null) {
			throw new RuntimeException("Nature MUST be defined");
		}
		this.nature = profile;
	}


	@XML_SetValue
	public void setSlots(String val) {
		String[] words = val.split(" *, *");
		int n = words.length;
		for(int i=0; i<n; i++) {
			int slot = Integer.valueOf(words[i]);

			addSlot(slot);
		}
	}

	
	/**
	 * 
	 */
	public ProfileMappingBuilder() {
		super();
		currentSlots = new int[]{};
		currentNbSlots = currentSlots.length;
	}


	/**
	 * 
	 * @param slot
	 */
	public void addSlot(int slot) {

		int k=0;
		boolean isDuplicate = false;
		while(isDuplicate && k<currentNbSlots) {
			isDuplicate = (currentSlots[k++]==slot);
		}

		if(!isDuplicate) {
			int[] extendedSlots = new int[currentNbSlots+1];
			if(currentNbSlots>0) {
				for(int i=0; i<currentNbSlots; i++) {
					extendedSlots[i] = currentSlots[i];
				}	
			}
			extendedSlots[currentNbSlots] = slot;
			currentSlots = extendedSlots;
			currentNbSlots++;
		}
	}


	/**
	 * 
	 * @param nUnits
	 * @return
	 */
	public ProfileMapping build(int nUnits) {
		if(nature==null) {
			throw new RuntimeException("Nature MUST be defined");
		}

		for(int i=0; i<currentNbSlots; i++) {
			if(currentSlots[i]>nUnits-1) {
				throw new RuntimeException("Illegal slot: "+i+">"+currentSlots[i]);
			}
		}
		
		if(currentNbSlots>1) {
			return new RangeProfileMapping(nature, currentSlots);	
		}
		else if(currentNbSlots==1) {
			return new SingletonProfileMapping(nature, currentSlots[0]);
		}
		else {
			throw new RuntimeException("No slots have been defined for this mapping!!");
		}
		
	}
}

