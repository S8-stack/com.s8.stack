package com.s8.io.svg.shapes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.ViewBox;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Group extends SVG_Shape {

	protected List<SVG_Shape> shapes;


	/**
	 * 
	 */
	public SVG_Group() {
		super();
		shapes = new ArrayList<SVG_Shape>();
	}
	
	public SVG_Group(String className) {
		super(className);
		shapes = new ArrayList<SVG_Shape>();
	}
	
	private SVG_Group(String className, List<SVG_Shape> shapes) {
		super(className);
		this.shapes = shapes;
	}
	
	
	/**
	 * Null shape ignored.
	 * 
	 * @param shape
	 */
	public void add(SVG_Shape shape){
		if(shape!=null) {
			shapes.add(shape);	
		}
	}


	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		for(SVG_Shape shape : shapes){
			shape.updateBoundingBox(box);
		}
	}

	
	@Override
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		writer.append("<g");
		printAttributes(writer);
		writer.append(">\n");
		for(SVG_Shape shape : shapes){
			shape.print(writer, viewBox);
		}
		writer.append("</g>\n");
	}

	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		List<SVG_Shape> transformedShapes = new ArrayList<>(shapes.size());
		for(SVG_Shape shape : shapes) {
			transformedShapes.add(shape.rewrite(transform));
		}
		return new SVG_Group(className, transformedShapes);
	}


}
