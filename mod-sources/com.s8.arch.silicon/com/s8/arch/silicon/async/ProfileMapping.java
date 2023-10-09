package com.s8.arch.silicon.async;

public abstract class ProfileMapping {
	
	private final MthProfile profile;
	
	
	public ProfileMapping(MthProfile profile) {
		super();
		this.profile = profile;
	}
	
	
	public MthProfile getProfile() {
		return profile;
	}
	
	public abstract int getSlot();
	


	public static ProfileMapping createDefault(MthProfile profile) {
		return new SingletonProfileMapping(profile, 0);
	}
}
