package com.s8.io.svg;

/**
 * 
 * @author pierreconvert
 *
 */
public interface SVG_Vector {

	
	public double getX();
	
	
	public double getY();
	
	
	/**
	 * 
	 * @author pierreconvert
	 *
	 */
	public static class Vec implements SVG_Vector {
	
		/**
		 * 
		 * @return x coordinate of the vector
		 */
		public double x;
		
		
		/**
		 * 
		 * @return y coordinate of the vector
		 */
		public double y;
		
		
		public Vec() {
			super();
		}
		
		public Vec(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}


		@Override
		public double getX() {
			return x;
		}


		@Override
		public double getY() {
			return y;
		}
	}
	
}
