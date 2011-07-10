package jbamboo.elements;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

/**
 * Finite Elements. This is the whole kit and kaboodle. These things have a really hairy construction mechanism.
 * @author robertdfrench
 *
 */
public abstract class FiniteElement extends JBambooNamespace {
		
	/**
	 * This is a dummy constructor for use by the ElementFactory
	 */
	protected FiniteElement() {
		
	}
	
	@Override
	/**
	 * determines whether two elements are the same
	 */
	public abstract boolean equals(Object o);
	
	/**
	 * integrates a RealFunction f over the domain of the element using numPoints samples
	 * @param f
	 * @param numPoints
	 * @return a numerical integral
	 */
	public abstract Double integrate(RealFunction f, Natural numPoints);
	
	/**
	 * Calculates a set of <b>numPoints</b> points contained within the interval and applies the RealFunction <b>f</b>
	 * to each of them.
	 * @param f
	 * @param numPoints
	 * @return an array of Doubles containing the point values
	 */
	public abstract Double[] apply(RealFunction f, Natural numPoints);
	
	/**
	 * Should be removed, this only applies to IntervalElements
	 * @return the length of the element
	 */
	// TODO: Remove
	public abstract Double getLength();
	
	/**
	 * This might also need to be removed, because it depends on the element having 
	 * one point which can be assumed to be the "origin". 
	 * @param localPoint
	 * @return
	 */
	public abstract Point translateToGlobalPoint(Point localPoint);
	
	/**
	 * Determines whether the element contains the given point
	 * @param p
	 * @return
	 */
	public abstract boolean contains(Point p);
	
	/**
	 * String representation
	 */
	public abstract String toString();
	
	/**
	 * This is the overdrawn magic that allows the MeshSyntesizer to clone new objects based on their name.
	 * I think it's obtuse and stupid
	 * @param points
	 * @return
	 * @throws InvalidElementException
	 */
	protected abstract FiniteElement semiconstructor(Point ... points) throws InvalidElementException;
	
	private Point[] points;
	
	/**
	 * Get the array of points that define the element
	 * @return
	 */
	public Point[] getPoints() {
		return points;
	}
	
	/**
	 * Must be called during one of the construction routines
	 * @param numPoints
	 */
	protected void initializePoints(Natural numPoints) {
		points = new Point[numPoints.toInt()];
	}
	
	/**
	 * For accessing points directly
	 * @param i
	 * @param p
	 */
	protected void setPoint(Natural i, Point p) {
		points[i.toInt() - 1] = p;
	}

}

