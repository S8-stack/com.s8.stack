package com.s8.io.svg.shapes;

import java.io.IOException;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.ViewBox;
import com.s8.io.svg.ViewBoxUpdateType;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Rectangle extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	protected double x1;

	protected double y1;

	protected double x2;

	protected double y2;






	/**
	 * 
	 */
	public SVG_Rectangle() {
		super();
	}


	public SVG_Rectangle(String className, double x1, double y1, double x2, double y2) {
		super(className);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}


	public SVG_Rectangle(String className, double[] p1, double[] p2) {
		super(className);
		this.x1 = p1[0];
		this.y1 = p1[1];
		this.x2 = p2[0];
		this.y2 = p2[1];
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

		double x = adjusted_x1;
		double y = Math.min(adjusted_y1, adjusted_y2);
		double width = Math.abs(adjusted_x2-adjusted_x1);
		double height = Math.abs(adjusted_y2-adjusted_y1);

		builder.append("<rect");
		printAttributes(builder);
		builder.append(" x=\""+x+"\" y=\""+y+"\" width=\""+width+"\" height=\""+height+"\"/>\n");
	}


	public void setFirstPoint(double[] p1) {
		this.x1 = p1[0];
		this.y1 = p1[1];
	}


	public void setSecondPoint(double[] p2) {
		this.x2 = p2[0];
		this.y2 = p2[1];
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		throw new RuntimeException("Not implemented yet");
	}


}
