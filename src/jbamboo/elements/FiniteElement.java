package jbamboo.elements;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.functions.RealFunction;

public abstract class FiniteElement extends JBambooNamespace {
	
	public abstract Double integrate(RealFunction f, Natural numPoints);
	public abstract Double getLength();
	public abstract Point translateToGlobalPoint(Point localPoint);
	public abstract boolean contains(Point p);
	public abstract String toString();
	
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

