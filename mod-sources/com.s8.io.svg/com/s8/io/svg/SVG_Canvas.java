package com.s8.io.svg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.svg.shapes.SVG_Shape;



/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Canvas {

	public final static DecimalFormat FORMAT = new DecimalFormat("0.##");

	static{
		DecimalFormatSymbols symbols = FORMAT.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
	}



	private final static String HEADER_LINE1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
	private final static String HEADER_LINE2 = "<?xml-stylesheet href='mystyle.css' type='text/css'?>\n";

	private final static String XMLNS = "http://www.w3.org/2000/svg";

	//public final static String xmlRegex = "(<\\?.*\\?>)(<.*>)";

	/*
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 */



	protected List<SVG_Shape> shapes;


	protected boolean hasBeenAdjusted = false;


	protected final static int MAX_NUMBER_OF_SHAPES = (int) 1e6;

	protected int shapeCount;
	
	private final ViewBox viewBox;


	/**
	 * Total width of the canvas (height is size accordingly, keeping ratio)
	 */
	public double width = 1024;

	public double leftPadding = 64;

	public double rightPadding = 64;
	
	public double topPadding = 16;

	public double bottomPadding = 16;


	public SVG_Canvas(){
		super();
		viewBox = new ViewBox(this);
		initialize();
	}
	

	private void initialize() {
		shapes = new ArrayList<SVG_Shape>();
		shapeCount = 0;
	}


	/**
	 * Null shape ignored
	 * @param shape
	 */
	public void add(SVG_Shape shape){
		if(shape!=null) {
			if(shapeCount > MAX_NUMBER_OF_SHAPES){
				throw new RuntimeException("max number of shapes exceed: "+MAX_NUMBER_OF_SHAPES);
			}
			shapes.add(shape);
			shapeCount++;	
		}
	}



	/**
	 * Print shapes
	 * @param writer
	 * @throws IOException
	 */
	public void print(String pathname) {
		try {
			// print shapes
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(pathname)));

			// add header
			writer.append(HEADER_LINE1);
			writer.append(HEADER_LINE2);

			writer.append(printToHTML(width));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String printToHTML(double width) throws IOException {
		// compute the bounding box

		SVG_BoundingBox2D boundingBox2d = viewBox.getBoundingBox();
		for(SVG_Shape shape : shapes){
			shape.updateBoundingBox(boundingBox2d);
		}


		// initialize transformations
		viewBox.compile();


		StringBuilder builder = new StringBuilder();
		builder.append("<svg version=\"1.1\" ");
		viewBox.print(builder);
		builder.append(" xmlns=\""+XMLNS+"\">\n");
		for(SVG_Shape shape : shapes){
			shape.print(builder, viewBox);
		}
		builder.append("</svg>");
		return builder.toString();
	}

}
