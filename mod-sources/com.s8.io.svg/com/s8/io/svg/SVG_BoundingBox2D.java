package com.s8.io.svg;

import com.s8.io.svg.SVG_Vector.Vec;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_BoundingBox2D {

	public double xMin, xMax;

	public double yMin, yMax;

	private boolean isInitialized = false;



	public SVG_BoundingBox2D(){
		super();
	}
	
	public SVG_BoundingBox2D(double xMin, double xMax, double yMin, double yMax){
		super();
		isInitialized = true;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	public void update(SVG_Vector vertex){
		update(vertex.getX(), vertex.getY());
	}
	
	
	/**
	 * 
	 * @param v
	 */
	public void update(Vec v) {
		update(v.x, v.y);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void update(double x, double y) {
		if(!isInitialized){
			xMin = x;
			xMax = x;
			yMin = y;
			yMax = y;
			isInitialized = true;
		}
		else{
			xMin = Math.min(xMin, x);
			xMax = Math.max(xMax, x);
			yMin = Math.min(yMin, y);
			yMax = Math.max(yMax, y);
		}
	}




	/**
	 * 
	 * @return
	 */
	public double getRadius(){
		double xLength = xMax-xMin, yLength = yMax-yMin;
		return Math.sqrt(xLength*xLength+yLength*yLength);
	}


	/**
	 * 
	 * @return
	 */
	public double getVolume(){
		double xLength = xMax-xMin, yLength = yMax-yMin;
		return xLength*yLength;
	}



	public double getXMin(){
		return xMin;
	}

	public double getXMax(){
		return xMax;
	}
	
	public double getYMin(){
		return yMin;
	}

	public double getYMax(){
		return yMax;
	}

	

}
