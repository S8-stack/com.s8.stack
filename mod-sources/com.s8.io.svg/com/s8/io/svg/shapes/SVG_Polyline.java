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
public class SVG_Polyline extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	private boolean isPointsVisible;
	
	private int nVertices;

	private double pointsRadius;

	private SVG_Vector[] vertices;


	/**
	 * 
	 */
	public SVG_Polyline() {
		super();
	}

	
	/**
	 * 
	 * @param className
	 * @param coordinates
	 */
	public SVG_Polyline(String className, double[] coordinates){
		super(className);
		nVertices = coordinates.length/2;
		vertices = new SVG_Vector[nVertices];
		int i=0;
		for(int index=0; index<nVertices; index++) {
			vertices[index] = new Vec(coordinates[i++], coordinates[i++]);
		}
	}
	
	
	/**
	 * 
	 * @param className
	 * @param points
	 */
	public SVG_Polyline(String className, SVG_Vector[] points){
		super(className);
		nVertices = points.length;
		vertices = points;
	}
	

	/**
	 * 
	 * @param className
	 * @param coordinates
	 * @param pointsRadius
	 */
	public SVG_Polyline(String className, double[] coordinates, double pointsRadius){
		super(className);
		nVertices = coordinates.length/2;
		vertices = new SVG_Vector[nVertices];
		int i=0;
		for(int index=0; index<nVertices; index++) {
			vertices[index] = new Vec(coordinates[i++], coordinates[i++]);
		}
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
	}
	
	
	/**
	 * 
	 * @param className
	 * @param points
	 * @param pointsRadius
	 */
	public SVG_Polyline(String className, SVG_Vector[] points, double pointsRadius){
		super(className);
		nVertices = points.length;
		vertices = points;
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
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





	/**
	 *  <polyline points="20,20 40,25 60,40 80,120 120,140 200,180" class="er" />
	 * @param builder
	 * @throws IOException
	 */
	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<polyline");
		printAttributes(builder);
		builder.append(" points=\"");
		
		double x, y;
		SVG_Vector vertex;
		for(int i=0; i<nVertices; i++){
			vertex = vertices[i];
			x = viewBox.xTransform(vertex.getX());
			y = viewBox.yTransform(vertex.getY());
			builder.append(x+",");
			builder.append(y+" ");
		}
		builder.append("\"/>\n");

		if(isPointsVisible){
			for(int i=0; i<nVertices; i++){
				vertex = vertices[i];
				x = viewBox.xTransform(vertex.getX());
				y = viewBox.yTransform(vertex.getY());
				new SVG_Circle(className, x, y, pointsRadius).print(builder, viewBox);
			}
		}
	}

	@Override
	public SVG_Polyline rewrite(SVG_Rewriter transform) {
		SVG_Vector[] transformedVertices = new SVG_Vector[nVertices];
		for(int i=0; i<nVertices; i++) {
			transformedVertices[i] = transform.onPoint(vertices[i]);
		}
		return new SVG_Polyline(className, transformedVertices, pointsRadius);
	}
}
