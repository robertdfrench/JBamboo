package jbamboo.elements;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidSemiconstructorAuthorization;
import jbamboo.functions.RealFunction;
import jbamboo.tesselationpolicy.SemiconstructorAuthorization;

public abstract class FiniteElement extends JBambooNamespace {
	
	private SemiconstructorAuthorization token;
	
	/**
	 * This constructor should remain empty
	 */
	protected FiniteElement() {
		
	}
	
	public FiniteElement(SemiconstructorAuthorization token) throws InvalidSemiconstructorAuthorization {
		this.token = token;
	}
	
	public abstract Double integrate(RealFunction f, Natural numPoints);
	public abstract Double getLength();
	public abstract Point translateToGlobalPoint(Point localPoint);
	public abstract boolean contains(Point p);
	public abstract String toString();
	public abstract FiniteElement semiconstructor(SemiconstructorAuthorization token, Point ... points) throws InvalidElementException, InvalidSemiconstructorAuthorization;
	
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
	
	protected void validateToken(SemiconstructorAuthorization token) throws InvalidSemiconstructorAuthorization {
		if (token != this.token) throw new InvalidSemiconstructorAuthorization();
	}

}

