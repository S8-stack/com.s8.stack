package com.s8.io.svg.shapes;

import com.s8.io.svg.SVG_Vector;



/**
 * 
 * @author pierreconvert
 *
 */
public interface SVG_Rewriter {
	
	public abstract SVG_Vector onPoint(double x, double y);
	
	
	public default SVG_Vector onPoint(SVG_Vector point) {
		return onPoint(point.getX(), point.getY());
	}

	
	public abstract SVG_Vector onVector(double x, double y);
	
	
	public default SVG_Vector onVector(SVG_Vector vector) {
		return onVector(vector.getX(), vector.getY());
	}
	
	
	
	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public abstract double onScalar(double scalar);
	
	
	/**
	 * 
	 * 
	 * @return true is the transformation is acting as an <b>EXACT</b> quarter, half
	 *         or three quarters of a turn rotation.
	 */
	public abstract boolean isRotationExact();


}
