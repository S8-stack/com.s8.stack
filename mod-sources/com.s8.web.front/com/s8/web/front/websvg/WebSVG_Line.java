package com.s8.web.front.websvg;

import com.s8.api.objects.web.WebS8Session;
import com.s8.io.svg.SVG_Vector;

/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Line extends WebSVG_Element {
	

	/**
	 * 
	 * @param branch
	 * @param color
	 * @param solidity
	 * @param thickness
	 * @param xc
	 * @param yc
	 * @param r
	 * @return
	 */
	public static WebSVG_Line create(WebS8Session branch, 
			double thickness,
			WebSVG_StrokeSolidity solidity,
			WebSVG_StrokeColor color,
			double x0, double y0, 
			double x1, double y1,
			boolean isBoundingBoxUpdating) {
		WebSVG_Line circle = new WebSVG_Line(branch);
		circle.setStrokeColor(color);
		circle.setStrokeSolidity(solidity);
		circle.setStrokeThickness((float) thickness);
		circle.setCoordinates((float) x0, (float) y0, (float) x1, (float) y1);
		circle.isBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
	}
	
	/**
	 * 
	 * @param branch
	 * @param color
	 * @param solidity
	 * @param thickness
	 * @param xc
	 * @param yc
	 * @param r
	 * @return
	 */
	public static WebSVG_Line create(WebS8Session branch, 
			float thickness,
			WebSVG_StrokeSolidity solidity,
			WebSVG_StrokeColor color,
			SVG_Vector p0, SVG_Vector p1,
			boolean isBoundingBoxUpdating) {
		WebSVG_Line circle = new WebSVG_Line(branch);
		circle.setStrokeColor(color);
		circle.setStrokeSolidity(solidity);
		circle.setStrokeThickness(thickness);
		circle.setCoordinates(p0, p1);
		circle.isBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
	}
	

	public static WebSVG_Line create(WebS8Session branch, float x0, float y0, float x1, float y1) {
		WebSVG_Line line = new WebSVG_Line(branch);
		line.setCoordinates(x0, y0, x1, y1);
		return line;
	}
	
	
	public WebSVG_Line(WebS8Session branch) {
		super(branch, "/s8-web-front/websvg/WebSVG_Line");
	}
	
	
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(float x0, float y0, float x1, float y1) {
		setCoordinates(new float[] { x0, y0, x1, y1});
	}
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(SVG_Vector p0, SVG_Vector p1) {
		setCoordinates(new float[] { 
				(float) p0.getX(), (float) p0.getY(), 
				(float) p1.getX(), (float) p1.getY()});
	}
	
	

	/**
	 * following order: {x0, y0, x1, y1}
	 * @param coordinates
	 */
	public void setCoordinates(float[] coordinates) {
		vertex.fields().setFloat32ArrayField("coordinates", coordinates);
	}

	
}
