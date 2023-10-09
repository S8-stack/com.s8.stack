package com.s8.io.csv;

public interface CSV_Unit {
	
	
	public interface Base {
		
		public CSV_Unit getUnit(String abbreviation);
		
	}
	
	
	public final static CSV_Unit.Base STUB_FACTORY = new Base() {
		@Override
		public CSV_Unit getUnit(String abbreviation) {
			throw new RuntimeException("Stub placeholder");
		}
	};
	
	
	/**
	 * 
	 * @param value
	 * @return the SI value converted into this unit
	 */
	public double convert(double primaryValue);
	
	
	/**
	 * Convert the value passed as argument back into the SI unit system.
	 * 
	 * @param SI_value
	 * @return the SI unit retrieved from the value passed as argument (which in <code>this</code> unit)
	 */
	public double revert(double secondaryValue);


}
