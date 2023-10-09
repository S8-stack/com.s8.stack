package com.s8.web.front.websvg;

import com.s8.api.objects.web.WebS8Session;
import com.s8.io.svg.SVG_Vector;

/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Circle extends WebSVG_Element {

	
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
	public static WebSVG_Circle create(WebS8Session branch, 
			float thickness,
			WebSVG_StrokeSolidity solidity,
			WebSVG_StrokeColor color,
			double xc, double yc, 
			double r,
			boolean isBoundingBoxUpdating) {
		WebSVG_Circle circle = new WebSVG_Circle(branch);
		circle.setStrokeColor(color);
		circle.setStrokeSolidity(solidity);
		circle.setStrokeThickness(thickness);
		circle.setCenter(xc, yc);
		circle.setRadius(r);
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
	public static WebSVG_Circle create(WebS8Session branch, 
			double thickness,
			WebSVG_StrokeSolidity solidity,
			WebSVG_StrokeColor color,
			SVG_Vector center,
			double r,
			boolean isBoundingBoxUpdating) {
		WebSVG_Circle circle = new WebSVG_Circle(branch);
		circle.setStrokeColor(color);
		circle.setStrokeSolidity(solidity);
		circle.setStrokeThickness((float) thickness);
		circle.setCenter(center);
		circle.setRadius(r);
		circle.isBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
	}


	public WebSVG_Circle(WebS8Session branch) {
		super(branch, "/s8-web-front/websvg/WebSVG_Circle");
	}

	
	/**
	 * 
	 * @param point
	 */
	public void setCenter(SVG_Vector point) {
		vertex.fields().setFloat32ArrayField("center", new float[] {
				(float) point.getX(), (float) point.getY()
		});
	}
	

	public void setCenter(float x0, float y0) {
		vertex.fields().setFloat32ArrayField("center", new float[] { x0, y0});
	}
	
	public void setCenter(float[] coordinates) {
		vertex.fields().setFloat32ArrayField("center", coordinates);
	}
	
	public void setCenter(double x0, double y0) {
		setCenter(new float[] { (float) x0, (float) y0});
	}
	
	
	/**
	 * 
	 * @param radius
	 */
	public void setRadius(float radius) {
		vertex.fields().setFloat32Field("radius", radius);
	}
	
	public void setRadius(double radius) {
		setRadius((float) radius);
	}




}
