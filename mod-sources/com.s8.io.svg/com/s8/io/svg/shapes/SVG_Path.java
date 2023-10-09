package com.s8.io.svg.shapes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.svg.SVG_BoundingBox2D;
import com.s8.io.svg.SVG_Vector;
import com.s8.io.svg.ViewBox;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Path extends SVG_Shape {


	private class Position {
		public double x, y;
	}

	private abstract class Element {

		public abstract void update(Position position, SVG_BoundingBox2D box);

		public abstract void print(StringBuilder builder, ViewBox viewBox);

		public abstract Element transform(SVG_Rewriter transform);

	}

	private class MoveTo extends Element {

		private double x, y;

		public MoveTo(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public MoveTo(SVG_Vector point) {
			super();
			this.x = point.getX();
			this.y = point.getY();
		}

		@Override
		public void update(Position p, SVG_BoundingBox2D box) {
			p.x=x;
			p.y=y;
			box.update(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("M"+box.xTransform(x)+','+box.yTransform(y));
		}


		@Override
		public Element transform(SVG_Rewriter transform) {
			return new MoveTo(transform.onPoint(x, y));
		}
	}


	private class Line extends Element {

		private double dx, dy;

		public Line(double dx, double dy) {
			super();
			this.dx = dx;
			this.dy = dy;
		}
		
		public Line(SVG_Vector vector) {
			super();
			this.dx = vector.getX();
			this.dy = vector.getY();
		}

		@Override
		public void update(Position p, SVG_BoundingBox2D box) {
			p.x+=dx;
			p.y+=dy;
			box.update(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("l"+box.dxScale(dx)+","+box.dyScale(dy));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			return new Line(transform.onVector(dx, dy));
		}
	}
	
	private class LineTo extends Element {

		private double x, y;

		public LineTo(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public LineTo(SVG_Vector point) {
			super();
			this.x = point.getX();
			this.y = point.getY();
		}

		@Override
		public void update(Position p, SVG_BoundingBox2D box) {
			p.x=x;
			p.y=y;
			box.update(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("L"+box.xTransform(x)+","+box.yTransform(y));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			return new LineTo(transform.onPoint(x, y));
		}
	}

	private class Horizontal extends Element {

		private double dx;

		public Horizontal(double dx) {
			super();
			this.dx = dx;
		}

		@Override
		public void update(Position p, SVG_BoundingBox2D box) {
			p.x+=dx;
			box.update(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("h"+box.dxScale(dx));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			SVG_Vector transformedVector = transform.onVector(dx, 0); 
			if(transform.isRotationExact() && Math.abs(transformedVector.getY())<1e-6) {
				return new Horizontal(transformedVector.getX());
			}
			else if(transform.isRotationExact() && Math.abs(transformedVector.getY())<1e-6){
				return new Vertical(transformedVector.getY());
			}
			else {
				return new Line(transformedVector.getX(), transformedVector.getY());
			}
		}
	}
	

	private class Vertical extends Element {

		private double dy;

		public Vertical(double dy) {
			super();
			this.dy = dy;
		}

		@Override
		public void update(Position p, SVG_BoundingBox2D box) {
			p.y+=dy;
			box.update(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("v"+box.dyScale(dy));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			SVG_Vector transformedVector = transform.onVector(0, dy); 
			if(transform.isRotationExact() && Math.abs(transformedVector.getY())<1e-6) {
				return new Horizontal(transformedVector.getX());
			}
			else if(transform.isRotationExact() && Math.abs(transformedVector.getX())<1e-6){
				return new Vertical(transformedVector.getY());
			}
			else {
				return new Line(transformedVector.getX(), transformedVector.getY());
			}
		}

	}


	/**
	 *  a rx ry x-axis-rotation large-arc-flag sweep-flag dx dy
	 * @author pc
	 *
	 */
	private class Arc extends Element {

		private double r;
		private boolean isLargeArc;
		private boolean isCounterClockwise;
		private double dx;
		private double dy;

		public Arc(double r, boolean isLargeArc, boolean isCounterClockwise, double dx, double dy) {
			super();
			this.r = r;
			this.isLargeArc = isLargeArc;
			this.isCounterClockwise = isCounterClockwise;
			this.dx = dx;
			this.dy = dy;
		}
		
		public Arc(double r, boolean isLargeArc, boolean isCounterClockwise, SVG_Vector vector) {
			super();
			this.r = r;
			this.isLargeArc = isLargeArc;
			this.isCounterClockwise = isCounterClockwise;
			this.dx = vector.getX();
			this.dy = vector.getY();
		}

		@Override
		public void update(Position position, SVG_BoundingBox2D box) {
			position.x+=dx;
			position.y+=dy;
			box.update(position.x+r, position.y+r);
			box.update(position.x-r, position.y+r);
			box.update(position.x-r, position.y-r);
			box.update(position.x+r, position.y-r);
		}


		/**
		 *  a rx ry x-axis-rotation large-arc-flag sweep-flag dx dy
		 */
		@Override
		public void print(StringBuilder builder, ViewBox box) {
			double scaledRadius = box.scale(r);
			builder.append("a"+scaledRadius+','+scaledRadius+' '+0+' '
					+(isLargeArc?'1':'0')+' ' // large-arc-flag
					+(isCounterClockwise?'1':'0')+' ' // sweep-flag
					+box.dxScale(dx)+','+box.dyScale(dy));
		}

	
		@Override
		public Element transform(SVG_Rewriter transform) {
			SVG_Vector transformedVector = transform.onVector(dx, dy); 
			return new Arc(transform.onScalar(r), isLargeArc, isCounterClockwise, transformedVector);
		}
	
	}

	private class CloseLoop extends Element {

		@Override
		public void update(Position position, SVG_BoundingBox2D box) {
			// nothing to update
		}

		@Override
		public void print(StringBuilder builder, ViewBox viewBox) {
			builder.append("z");
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			return new CloseLoop();
		}
	

	}

	private List<Element> elements;


	public SVG_Path() {
		super();
		initialize();
	}

	public SVG_Path(String className) {
		super(className);
		initialize();
	}
	
	
	private SVG_Path(String className, List<Element> elements) {
		super(className);
		this.elements = elements;
	}
	
	
	private void initialize() {
		elements = new ArrayList<>();
	}

	public SVG_Path moveTo(double x, double y) {
		elements.add(new MoveTo(x, y));
		return this;
	}

	public SVG_Path line(double dx, double dy) {
		elements.add(new Line(dx, dy));
		return this;
	}

	public SVG_Path lineTo(double x, double y) {
		elements.add(new LineTo(x, y));
		return this;
	}

	public SVG_Path horizontal(double dx) {
		elements.add(new Horizontal(dx));
		return this;
	}

	public SVG_Path vertical(double dy) {
		elements.add(new Vertical(dy));
		return this;
	}

	public SVG_Path arc(double r, boolean isLargeArc, boolean isCounterClockwise, double dx, double dy) {
		elements.add(new Arc(r, isLargeArc, isCounterClockwise, dx, dy));
		return this;
	}

	public SVG_Path close() {
		elements.add(new CloseLoop());
		return this;
	}

	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box) {
		Position position = new Position();
		for(Element element : elements) {
			element.update(position, box);
		}
	}

	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<path"); // start path
		printAttributes(builder);
		builder.append(" d=\"");
		boolean isFirst = true;
		for(Element element : elements) {
			if(!isFirst) { builder.append(" "); } else { isFirst = false; }
			element.print(builder, viewBox);
		}
		builder.append("\"/>"); // end path	

	}

	
	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		List<Element> transformedElements = new ArrayList<>(elements.size());
		for(Element element : elements) {
			transformedElements.add(element.transform(transform));
		}
		return new SVG_Path(className, transformedElements);
	}

}
