package com.s8.io.svg.transform;

import com.s8.io.svg.ViewBox;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Translate extends SVG_Transform {

	private double dx;

	private double dy;


	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	public SVG_Translate(double dx, double dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}


	@Override
	public void print(StringBuilder builder, ViewBox box) {
		builder.append("translate(");
		builder.append(box.dxScale(dx));
		builder.append(",");
		builder.append(box.dxScale(dy));
		builder.append(")");
	}

}
