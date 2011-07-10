package jbamboo.functions;

import jbamboo.basetypes.Point;

/**
 * Simply a constructor class for 1st degree polynomials
 * @author robertdfrench
 *
 */
public class Monomial extends Polynomial {

	/**
	 * Used for constructing a line that intersects two given points, p and q.
	 * Suggested usage: Polynomial f = new Monomial(p,q);
	 * @param p
	 * @param q
	 */
	public Monomial(Point p, Point q) {
		Double slope = (p.y() - q.y()) / (p.x() - q.x());
		Double yIntercept = p.y() - slope * p.x();
		
		setCoefficient(1, slope);
		setCoefficient(0, yIntercept);
	}
}
