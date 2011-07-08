package jbamboo.elements;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

public abstract class FiniteElement extends JBambooNamespace {
		
	public static String typeName = "FiniteElement";
	/**
	 * This is a dummy constructor for use by the ElementFactory
	 */
	protected FiniteElement() {
		
	}
	
	@Override
	public abstract boolean equals(Object o);
	public abstract Double integrate(RealFunction f, Natural numPoints);
	public abstract Double[] apply(RealFunction f, Natural numPoints);
	public abstract Double getLength();
	public abstract Point translateToGlobalPoint(Point localPoint);
	public abstract boolean contains(Point p);
	public abstract String toString();
	protected abstract FiniteElement semiconstructor(Point ... points) throws InvalidElementException;
	
	private Point[] points;
	
	public Point[] getPoints() {
		return points;
	}
	
	protected void initializePoints(Natural numPoints) {
		points = new Point[numPoints.toInt()];
	}
	
	protected void setPoint(Natural i, Point p) {
		points[i.toInt() - 1] = p;
	}

}

