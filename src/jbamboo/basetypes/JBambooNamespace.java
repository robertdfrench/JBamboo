package jbamboo.basetypes;

import jbamboo.functions.RealFunction;


/**
 * This should be a place for idioms, not algorithms.
 * @author robertdfrench
 *
 */
public abstract class JBambooNamespace {
	/**
	 * Short description of the object's state
	 */
	public abstract String toString(); 

	/**
	 * Idiom for creating a new point from arbitrarily many doubles
	 * @param coordinates
	 * @return
	 */
	public static Point point(Double... coordinates) {
		Point p = new Point();
		Natural n = new Natural(coordinates.length);
		for(Integer i : n) {
			p.setCoordinate(i, coordinates[i - 1]);
		}
		return p;
	}
	
	/**
	 * Idiom for creating a new natural from an arbitrary int
	 * @param number
	 * @return new Natural representing the absolute value of <b>number</b>
	 */
	public static Natural natural(int number) {
		return new Natural(number);
	}
	
	/**
	 * Idiom for creating a new Natural from an arbitrary double
	 * @param number
	 * @return new Natural representing the absolute value of <b>(int) number</b>
	 */
	public static Natural natural(double number) {
		return new Natural(number);
	}

	/**
	 * Gets the 2nd derivative
	 * @param f
	 * @return
	 */
	public static RealFunction laplacian(RealFunction f) {
		return f.getDerivative().getDerivative();
	}
}
