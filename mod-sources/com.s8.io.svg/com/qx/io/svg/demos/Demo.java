package com.qx.io.svg.demos;

import com.s8.io.svg.SVG_Canvas;
import com.s8.io.svg.shapes.SVG_Circle;
import com.s8.io.svg.shapes.SVG_Line;
import com.s8.io.svg.shapes.SVG_Rectangle;

/**
 * 
 * @author pierreconvert
 *
 */
public class Demo {

	public static void main(String[] args){

		SVG_Canvas canvas = new SVG_Canvas();

		int exe = 2;
		switch(exe){
		case 1:
			canvas.add(new SVG_Circle("point", 0, 0, 2.0));
			canvas.add(new SVG_Circle("point", 0.5, 3, 2.0));
			canvas.add(new SVG_Line("dashed", 0, 0, 2,2));
			canvas.print("output/myFirstSVG.svg");
			break;
		case 2:
			canvas.add(new SVG_Rectangle("interior", 0.,  0., 300., 200.));
			canvas.add(new SVG_Line("dashed-grey", 0., 50., 300.,50.));
			canvas.add(new SVG_Line("dashed-grey", 0., 100., 300.,100.));
			canvas.add(new SVG_Line("dashed-grey", 0., 150., 300.,150.));
			
			canvas.add(new SVG_Line("dashed-grey", 75., 0., 75.,200.));
			canvas.add(new SVG_Line("dashed-grey", 150., 0., 150.,200.));
			canvas.add(new SVG_Line("dashed-grey", 225., 0., 225.,200.));
			
			canvas.print("output/myFirstSVG.svg");
			break;
		}
	}
}
