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
public class SVG_Text extends SVG_Shape {

	

	public ViewBoxUpdateType type = ViewBoxUpdateType.Contained;
	
	protected double x;

	protected double y;
	
	protected String text;

	/**
	 * 
	 */
	public SVG_Text() {
		super();
	}


	public SVG_Text(String className, double x, double y, String text) {
		super(className);
		this.x = x;
		this.y = y;
		this.text=text;
	}
	
	
	public SVG_Text(String className, SVG_Vector point, String text) {
		super(className);
		this.x = point.getX();
		this.y = point.getY();
		this.text=text;
	}


	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		//int a=text.toCharArray().length;
		box.update(x, y);
		//viewBox.updateBoundingBox(x+(viewBox.xMax-viewBox.xMin)/200*a, y);
		//viewBox.updateBoundingBox(x, y+(viewBox.yMax-viewBox.yMin)/50);
	}

	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		//exemple 
		//<text x="250" y="150" font-family="Verdana" font-size="55" fill="blue" >
		//Hello, out there
		//</text>
		double adjusted_x= viewBox.xTransform(x);
		double adjusted_y= viewBox.yTransform(y);
		builder.append("<text");
		printAttributes(builder);
		builder.append(" x=\""+fm(adjusted_x)+"\" y=\""+fm(adjusted_y)+"\">\n"+text+"\n</text>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		return new SVG_Text(className, transform.onPoint(x, y), text);
	}
	
	
}
