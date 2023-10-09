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
public class SVG_Circle extends SVG_Shape {
	
	public ViewBoxUpdateType type = ViewBoxUpdateType.Contained;
	
	protected double xCenter, yCenter;

	protected double r;

	/**
	 * 
	 */
	public SVG_Circle() {
		super();
	}


	public SVG_Circle(String className, SVG_Vector center, double r) {
		super(className);
		this.xCenter = center.getX();
		this.yCenter = center.getY();
		this.r = r;
	}
	
	
	
	public SVG_Circle(String className, double cx, double cy, double r) {
		super(className);
		this.xCenter = cx;
		this.yCenter = cy;
		this.r = r;
	}

	



	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		switch(type){

		case Center : 
			box.update(xCenter, yCenter);
			break;

		case Contained : 
			box.update(xCenter-r, yCenter-r);
			box.update(xCenter-r, yCenter+r);
			box.update(xCenter+r, yCenter-r);
			box.update(xCenter+r, yCenter+r);
			break;

		default :
			break;

		}
	}


	@Override
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		double adjusted_cx = viewBox.xTransform(xCenter);
		double adjusted_cy = viewBox.yTransform(yCenter);
		double adjusted_r = viewBox.scale(r);
		// example :  <circle cx="100" cy="50" r="40" class="truc"/>
		writer.append("<circle");
		printAttributes(writer);
		writer.append(" cx=\""+adjusted_cx+"\" cy=\""+adjusted_cy+"\" r=\""+adjusted_r+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		return new SVG_Circle(className, transform.onPoint(xCenter, yCenter), transform.onScalar(r));
	}


}
