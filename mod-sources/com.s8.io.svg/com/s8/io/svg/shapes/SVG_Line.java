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
public class SVG_Line extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	/**
	 * points of the line
	 */
	protected double x1, y1, x2, y2;




	/**
	 * 
	 */
	public SVG_Line() {
		super();
	}


	public SVG_Line(String className, double x1, double y1, double x2, double y2) {
		super(className);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	public SVG_Line(String className, SVG_Vector point0, SVG_Vector point1) {
		super(className);
		this.x1 = point0.getX();
		this.y1 = point0.getY();
		this.x2 = point1.getX();
		this.y2 = point1.getY();
	}




	public SVG_Line(String className, double[] p1, double[] p2) {
		super(className);
		this.x1 = p1[0]; this.y1 = p1[1];
		this.x2 = p2[0]; this.y2 = p2[1];
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
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(x1);
		double adjusted_y1 = viewBox.yTransform(y1);
		double adjusted_x2 = viewBox.xTransform(x2);
		double adjusted_y2 = viewBox.yTransform(y2);
		
		builder.append("<line");
		printAttributes(builder);
		builder.append(" x1=\""+adjusted_x1+"\" "+
				" y1=\""+adjusted_y1+"\" "+ 
				" x2=\""+adjusted_x2+"\" "+
				" y2=\""+adjusted_y2+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		
		return new SVG_Line(className, transform.onPoint(x1, y1), transform.onPoint(x2, y2));
	}


	/*
	public void setFirstPoint(double[] p1) {
		this.p1 = new MathVector2d(p1);
	}


	public void setSecondPoint(double[] p2) {
		this.p2 = new MathVector2d(p2);
	}
	*/

}
