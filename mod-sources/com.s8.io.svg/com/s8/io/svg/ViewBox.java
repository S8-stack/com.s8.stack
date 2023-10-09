package com.s8.io.svg;

import java.text.DecimalFormat;


/**
 * 
 * @author pierreconvert
 *
 */
public class ViewBox {

	/**
	 * 4 meters objects print as 800px wide in view port
	 */
	public final static double DEFAULT_SCALING = 800/4.0;

	public final static double DEFAULT_PADDING = 0;

	public final static DecimalFormat DF = new DecimalFormat("0.####");

	private final SVG_Canvas canvas;

	private final SVG_BoundingBox2D boundingBox2D;

	private double x0;

	private double y1;

	private double xShift;

	private double yShift;

	private double width;

	private double height;

	private double scaling = 1.0;


	/**
	 * 
	 * @param scaling
	 * @param padding
	 */
	public ViewBox(SVG_Canvas canvas){
		super();
		this.canvas = canvas;
		boundingBox2D = new SVG_BoundingBox2D();
	}


	public SVG_BoundingBox2D getBoundingBox() {
		return boundingBox2D;
	}


	public void compile() {
		xShift = canvas.leftPadding;
		yShift = canvas.topPadding;

		x0 = boundingBox2D.xMin;
		y1 = boundingBox2D.yMax;
		double x1 = boundingBox2D.xMax;
		double y0 = boundingBox2D.yMin;

		scaling = (canvas.width-canvas.leftPadding-canvas.rightPadding)/(x1-x0);
		width = canvas.width;
		height = (y1-y0)*scaling+canvas.topPadding+canvas.bottomPadding;
	}


	public double xTransform(double x) {
		return (x-x0)*scaling+xShift;
	}

	public double dxScale(double dx) {
		return (dx)*scaling;
	}

	public double yTransform(double y) {
		return (y1-y)*scaling+yShift;
	}

	public double dyScale(double dy) {
		return (-dy)*scaling;
	}

	public double scale(double d) {
		return d*scaling;
	}


	/**
	 * builder.append("<svg version=\"1.1\" viewBox=\""+viewBox.toString()+"\" width=\""+width+"\" height=\""+height+"\" margin=\""+MARGIN+"\" xmlns=\""+XMLNS+"\">\n");
	 */
	public void print(StringBuilder builder) {

		// width
		builder.append("width=\"");
		builder.append(DF.format(width));
		builder.append('\"');

		// height
		builder.append(" height=\"");
		builder.append(DF.format(height));
		builder.append('\"');

		// view box
		builder.append(" viewBox=\"0 0 ");
		builder.append(DF.format(width)+" ");
		builder.append(DF.format(height));
		builder.append('\"');
	}

}
