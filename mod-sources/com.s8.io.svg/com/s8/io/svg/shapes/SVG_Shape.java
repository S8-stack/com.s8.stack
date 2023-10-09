package com.s8.io.svg.shapes;

import java.io.IOException;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.ViewBox;


/**
 * 
 * @author pierreconvert
 *
 */
public abstract class SVG_Shape {

	/**
	 * inline style definition
	 */
	public String style;

	/**
	 * class reference for style definition
	 */
	public String className;
	
	
	
	/**
	 * 
	 */
	private String transform;

	
	public SVG_Shape() {
	}


	/**
	 * @param className
	 */
	public SVG_Shape(String className) {
		super();
		this.className = className;
	}
	
	
	public void setStyle(String style) {
		this.style = style;
	}
	
	
	public void appendTransform(String transform) {
		if(transform!=null) {
			if(this.transform!=null) {
				this.transform = this.transform+' '+transform;
			}
			else {
				this.transform = transform;	
			}	
		}
	}

	public abstract void updateBoundingBox(SVG_BoundingBox2D box);


	public abstract void print(StringBuilder builder, ViewBox viewBox) throws IOException;


	public abstract SVG_Shape rewrite(SVG_Rewriter rewriter);
	

	/**
	 * @return the style
	 */
	public String getStyleClass() {
		return className;
	}


	/**
	 * @param styleClass the style to set
	 */
	public void setStyleClass(String styleClass) {
		this.className = styleClass;
	}


	/**
	 * static helper
	 * @param value
	 * @return
	 */
	public static String fm(double value){
		//return FORMAT.format(value);
		return String.valueOf((int) value);
	}
	

	protected void printAttributes(StringBuilder builder) {
		if(className!=null){
			builder.append(" class=\""+className+"\"");
		}
		if(style!=null){
			builder.append(" style=\""+style+"\"");
		}
		if(transform!=null){
			builder.append(" transform=\""+transform+"\"");
		}
	}




}
