package com.s8.io.svg.shapes;



import java.io.IOException;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.SVG_Vector;
import com.s8.io.svg.ViewBox;
import com.s8.io.svg.ViewBoxUpdateType;



/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Arrow extends SVG_Shape {

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	protected double x1;

	protected double y1;

	protected double x2;

	protected double y2;

	/**
	 * 
	 */
	public SVG_Arrow() {
		super();
	}


	
	/**
	 * 
	 * @param style
	 * @param point0
	 * @param point1
	 */
	public SVG_Arrow(String style, SVG_Vector point0, SVG_Vector point1) {
		super(style);
		this.x1 = point0.getX();
		this.y1 = point0.getY();
		this.x2 = point1.getX();
		this.y2 = point1.getY();
	}

	
	/**
	 * 
	 * @param style
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public SVG_Arrow(String style, double x1, double y1, double x2, double y2) {
		super(style);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}



	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		switch(updateType){
		case Contained :
			box.update(x1, y1);
			box.update(x2, y2);
			break;

		default: break;
		}
	}


	@Override
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		/*	
		double adjusted_x1 = viewBox.transformXCoordinate(x1);
		double adjusted_y1 = viewBox.transformYCoordinate(y1);

		double adjusted_x2 = viewBox.transformXCoordinate(x2);
		double adjusted_y2 = viewBox.transformYCoordinate(y2);

		 */

	}


	
	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		throw new RuntimeException("Not implemented yet");
	}
}
