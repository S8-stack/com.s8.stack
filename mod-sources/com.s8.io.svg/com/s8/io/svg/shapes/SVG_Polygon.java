package com.s8.io.svg.shapes;


import java.io.IOException;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.SVG_Vector;
import com.s8.io.svg.ViewBox;
import com.s8.io.svg.ViewBoxUpdateType;
import com.s8.io.svg.SVG_Vector.Vec;



/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Polygon extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	/**
	 * x0, y0, x1, y1, x2, y2, ...
	 */
	private SVG_Vector[] vertices;


	private int nVertices;


	/**
	 * 
	 */
	public SVG_Polygon() {
		super();
	}



	public SVG_Polygon(String className, double[] coordinates){
		super(className);
		nVertices = coordinates.length/2;
		vertices = new SVG_Vector[nVertices];
		int i=0;
		for(int index=0; index<nVertices; index++) {
			vertices[index] = new Vec(coordinates[i++], coordinates[i++]);
		}
	}

	public SVG_Polygon(String className, SVG_Vector[] points){
		super(className);
		nVertices = points.length;
		vertices = points;
	}



	/*
	public SVG_Polygon(String style, double[][] polygon){
		super(style);

		int n = polygon.length;
		this.points = new MathVector2d[n];

		for(int i=0; i<n; i++){
			points[i] = new MathVector2d(polygon[i][0], polygon[i][1]);
		}
	}
	 */

	
	/**
	 * 
	 * @param style
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public SVG_Polygon(String style, double x0, double y0, double x1, double y1, double x2, double y2) {
		super(style);
		nVertices = 3;
		vertices = new SVG_Vector[] { new Vec(x0, y0), new Vec(x1, y1), new Vec(x2, y2)};
	}

	
	/**
	 * 
	 * @param style
	 * @param p0
	 * @param p1
	 * @param p2
	 */
	public SVG_Polygon(String style, SVG_Vector p0, SVG_Vector p1, SVG_Vector p2) {
		super(style);
		nVertices = 3;
		vertices = new SVG_Vector[] { p0, p1, p2};
	}




	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){

		switch(updateType){
		case Contained :
			for(int i=0; i<nVertices; i++){
				box.update(vertices[i]);	
			}
			break;
		default : break;
		}
	}


	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<polygon");
		printAttributes(builder);
		builder.append(" points=\"");
		
		// v
		SVG_Vector v;
		for(int i=0; i<nVertices; i++){
			v = vertices[i];
			builder.append(viewBox.xTransform(v.getX())+",");
			builder.append(viewBox.yTransform(v.getY())+" ");
		}
		builder.append("\"/>\n");
	}




	/**
	 * build rectangle!!
	 * @param style
	 * @param x0
	 * @param x1
	 * @param y0
	 * @param y1
	 * @return
	 */
	public static SVG_Polygon rectangle(String style, double x0, double x1, double y0, double y1){
		double[] coordinates = new double[]{ x0, y0, x1, y0, x1, y1, x0, y1};
		return new SVG_Polygon(style, coordinates);
	}



	@Override
	public SVG_Polygon rewrite(SVG_Rewriter transform) {
		SVG_Vector[] transformedVertices = new SVG_Vector[2*nVertices];
		for(int i=0; i<nVertices; i++) {
			transformedVertices[i] = transform.onPoint(vertices[i]);
		}
		return new SVG_Polygon(className, transformedVertices);
	}


	
	
	public static SVG_Polygon triangle(String styleClass, SVG_Vector p0, SVG_Vector p1, SVG_Vector p2) {
		return new SVG_Polygon(styleClass, p0, p1, p2);
	}


}
